package com.alatamli.web.helpers;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alatamli.web.entities.InstanceEntity;
import com.alatamli.web.entities.InstanceOtherEntity;
import com.alatamli.web.helpers.requests.AddDropletRequestHttp;
import com.alatamli.web.helpers.responses.InstanceResponse;
import com.alatamli.web.helpers.responses.digitalocean.DropletInstance;
import com.alatamli.web.helpers.responses.digitalocean.DropletsListResponse;
import com.alatamli.web.repositories.InstanceRepository;
import com.alatamli.web.requests.AddInstanceRequest;
import com.alatamli.web.shared.dto.AccountOneKeyDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class DigitaloceanCloudClient implements ICloudClient {
	
	InstanceRepository instanceRepository;

	
	public AccountOneKeyDto account ;
	
	public DigitaloceanCloudClient( AccountOneKeyDto account , InstanceRepository instanceRepository ) {
		this.account = account ;
		this.instanceRepository = instanceRepository;
	}
	
	public List<InstanceResponse>  getInstances() throws UnirestException, JsonMappingException, JsonProcessingException {
		

		HttpResponse<JsonNode> response = this.getInstancesHttp();
		
		String retryHeader = response.getBody().toString(); 
		
		if( response.getStatus() >= 300 )
			throw new RuntimeException( response.getBody().toString());
	
		
		ObjectMapper mapper = new ObjectMapper();
		DropletsListResponse dropletes = mapper.readValue(retryHeader, new TypeReference<DropletsListResponse>() {});
		
		
		List<InstanceResponse> instancesResponse = new ArrayList<>();
		
		
		for (DropletInstance instances : dropletes.getDroplets()) {
			
			InstanceEntity instanceEntity = instanceRepository.findByInstanceId( String.valueOf(instances.getId() ) );
			
			if( instanceEntity != null ) {
				
				instances.setDatabase(instanceEntity);
				
			}else {
				instanceEntity = new InstanceOtherEntity();
				instanceEntity.setInstanceId( String.valueOf( instances.getId() ) );
				instanceEntity.setName(instances.getName());
				instanceEntity.setMainIp(instances.getNetworks().getV4().get(1).getIp_address());
				InstanceEntity newInstanceEntity = instanceRepository.save(instanceEntity);
				instances.setDatabase(newInstanceEntity);
			}
			
			instancesResponse.add(instances);
			
		}
		
		
		return instancesResponse;
	
	}

	
	private HttpResponse<JsonNode> getInstancesHttp() throws UnirestException {
		String url = "https://api.digitalocean.com/v2/droplets" ;
		HttpResponse<JsonNode> response = Unirest.get(url)
				.header("Authorization", "Bearer "+this.account.getToken())
				.header("Content-Type", "application/json")
				.asJson();
		
		return response;
	}

	public List<InstanceResponse> AddInstances(AddInstanceRequest instanceRequest) throws JsonProcessingException, UnirestException {
		
		ObjectMapper mapper = new ObjectMapper();
		List<String> names  = this.generateName( instanceRequest.getName() , instanceRequest.getNumberInstances() );
		
		AddDropletRequestHttp instanceRequestHttp = new AddDropletRequestHttp();
		
		
		instanceRequestHttp.setNames(names);
		instanceRequestHttp.setRegion(instanceRequest.getRegion());
		
		
		String instanceRequestJson = mapper.writeValueAsString(instanceRequestHttp);
		
		HttpResponse<JsonNode> response = this.addInstancesHttp(instanceRequestJson);
		
		if( response.getStatus() < 300 ) {
			
			DropletsListResponse dropletes = mapper.readValue( response.getBody().toString() , new TypeReference<DropletsListResponse>() {});
			List<InstanceResponse> instancesResponse = new ArrayList<>();
			
			for (InstanceResponse instancesRes : dropletes.getDroplets()) {
				
				DropletInstance instances = (DropletInstance ) instancesRes ;
				
				InstanceEntity instanceEntity  = new InstanceOtherEntity();
				instanceEntity.setInstanceId( Long.toString( instances.getId()));
				instanceEntity.setName(instances.getName());
				if( instances.getNetworks().getV4().size() > 1 )
				instanceEntity.setMainIp( instances.getNetworks().getV4().get(1).getIp_address() );
				instanceEntity.setVmtaDomain(instanceRequest.getVmtaDomain());
				InstanceEntity newInstanceEntity = instanceRepository.save(instanceEntity);
				instancesRes.setDatabase(newInstanceEntity);
				
				instancesResponse.add(instancesRes);	
				
			}
				
			return instancesResponse;
			
		}else {
			throw new RuntimeException( response.getBody().toString());
		}
		
	}
	


	
	private HttpResponse<JsonNode> addInstancesHttp( String request ) throws UnirestException {
		
		HttpResponse<JsonNode> response = Unirest.post("https://api.digitalocean.com/v2/droplets")
			      .header("Authorization", "Bearer "+this.account.getToken())
			      .header("Content-Type", "application/json")
		          .body( request )
		          .asJson();
			
		return response;
		
	}
	
	private List<String> generateName( String name , int numberInstances){
		
		List<String> names = new ArrayList<>();

		Pattern pattern = Pattern.compile("([a-z0-9]{3})([0-9]{3})");
	    Matcher matcher = pattern.matcher(name);

		while (matcher.find()) {

			if(  matcher.groupCount() == 2 ) {
				int firstNumber=Integer.parseInt( matcher.group(2) );
				for( int i = firstNumber ; i < firstNumber + numberInstances ; i++ ) 
					names.add( matcher.group(1) + (new DecimalFormat("000")).format(i) );
			}
			
		}
		System.out.println( names );
		return names ;

	}

	public void deleteInstance(String instanceId) throws UnirestException {

		HttpResponse<JsonNode> response = this.deleteInstancesHttp(instanceId);
		
		if( response.getStatus() > 300 ) 
			throw new RuntimeException( response.getBody().toString());
		
	}

	
	private HttpResponse<JsonNode> deleteInstancesHttp(String instanceId) throws UnirestException {
	
		HttpResponse<JsonNode> response = Unirest.delete( "https://api.digitalocean.com/v2/droplets/"+instanceId )
				.header("Authorization", "Bearer "+this.account.getToken())
				.header("Content-Type", "application/json")
				.asJson();
		
		return response;
	}

	public void updateOption(String instanceId, String option) throws UnirestException {
		
		HttpResponse<JsonNode> response ; 
		
		switch(option) {
		
			case "stop" : 
					response = this.updateOptionsHttp("{\"type\":\"shutdown\"}" , instanceId );
				break;
				
			case "start" :  
					response = this.updateOptionsHttp("{\"type\":\"power_on\"}" , instanceId );
				break ;
				
			default :
				throw new RuntimeException( option + " option is not sepported " );
		
		}
		
		 
		
		if( response.getStatus() > 300 ) 
			throw new RuntimeException( response.getBody().toString());
		
	}

	
	private HttpResponse<JsonNode> updateOptionsHttp(String request , String instanceId ) throws UnirestException {
		
		HttpResponse<JsonNode> response = Unirest.post( "https://api.digitalocean.com/v2/droplets/"+instanceId+"/actions" )
			      .header("Authorization", "Bearer "+this.account.getToken())
			      .header("Content-Type", "application/json")
		          .body( request )
		          .asJson();
			
		return response;
	}

	
	

	
	
}
