package com.alatamli.web.helpers;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alatamli.web.helpers.requests.digitalocean.AddInstanceRequestHttp;
import com.alatamli.web.helpers.responses.DropletInstance;
import com.alatamli.web.helpers.responses.DropletsListResponse;
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

	
	public AccountOneKeyDto account ;
	
	public DigitaloceanCloudClient( AccountOneKeyDto account ) {
		this.account = account ;
	}
	
	public List<DropletInstance>  getInstances() throws UnirestException, JsonMappingException, JsonProcessingException {
		

		HttpResponse<JsonNode> response = this.getInstancesHttp();
		String retryHeader = response.getBody().toString(); 
		
		ObjectMapper mapper = new ObjectMapper();
		DropletsListResponse dropletes = mapper.readValue(retryHeader, new TypeReference<DropletsListResponse>() {});

		
		return dropletes.getDroplets();
	
	}

	@Override
	public HttpResponse<JsonNode> getInstancesHttp() throws UnirestException {
		String url = "https://api.digitalocean.com/v2/droplets" ;
		HttpResponse<JsonNode> response = Unirest.get(url)
				.header("Authorization", "Bearer "+this.account.getToken())
				.header("Content-Type", "application/json")
				.asJson();
		
		return response;
	}

	public List<DropletInstance> AddInstances(AddInstanceRequest instanceRequest) throws JsonProcessingException, UnirestException {
		
		ObjectMapper mapper = new ObjectMapper();

		List<String> names = this.generateName(instanceRequest.getName() , instanceRequest.getNumberInstances() );
	
		
		AddInstanceRequestHttp instanceRequestHttp = new AddInstanceRequestHttp();
		
		instanceRequestHttp.setNames(names);
		instanceRequestHttp.setRegion(instanceRequest.getRegion());
		
		
		String instanceRequestJson = mapper.writeValueAsString(instanceRequestHttp);
		
		HttpResponse<JsonNode> response = this.addInstancesHttp(instanceRequestJson);
		
		if( response.getStatus() < 300 ) {
			DropletsListResponse dropletes = mapper.readValue( response.getBody().toString() , new TypeReference<DropletsListResponse>() {});
			return dropletes.getDroplets(); 
		}else {
			throw new RuntimeException( response.getBody().toString());
		}
		
	}
	


	@Override
	public HttpResponse<JsonNode> addInstancesHttp( String request ) throws UnirestException {
		
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
					names.add( matcher.group(1) + i  );
			}
			
		}
		
		return names ;

	}

	public void deleteInstance(String instanceId) throws UnirestException {

		HttpResponse<JsonNode> response = this.deleteInstancesHttp(instanceId);
		
		if( response.getStatus() > 300 ) 
			throw new RuntimeException( response.getBody().toString());
		
	}

	@Override
	public HttpResponse<JsonNode> deleteInstancesHttp(String instanceId) throws UnirestException {
	
		HttpResponse<JsonNode> response = Unirest.delete( "https://api.digitalocean.com/v2/droplets/"+instanceId )
				.header("Authorization", "Bearer "+this.account.getToken())
				.header("Content-Type", "application/json")
				.asJson();
		
		return response;
	}

	
	

	
	
}
