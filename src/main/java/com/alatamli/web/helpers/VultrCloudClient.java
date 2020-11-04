package com.alatamli.web.helpers;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alatamli.web.entities.InstanceEntity;
import com.alatamli.web.entities.InstanceOtherEntity;
import com.alatamli.web.helpers.responses.InstanceResponse;
import com.alatamli.web.helpers.responses.vultr.InstanceVultr;
import com.alatamli.web.helpers.responses.vultr.InstanceVultrList;
import com.alatamli.web.repositories.InstanceRepository;
import com.alatamli.web.requests.AddInstanceRequest;
import com.alatamli.web.shared.dto.AccountOneKeyDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;




public class VultrCloudClient implements ICloudClient {

	InstanceRepository instanceRepository;
	private
	AccountOneKeyDto account ;
	
	public VultrCloudClient( AccountOneKeyDto account , InstanceRepository instanceRepository  ) {
		this.account = account ;
		this.instanceRepository = instanceRepository;
	}
	
	
	@Override
	public List<InstanceResponse> getInstances()
			throws UnirestException, JsonMappingException, JsonProcessingException {
		
		
		HttpResponse<JsonNode> response = this.getInstancesHttp();
		
		String bodyResponse = response.getBody().toString(); 
		
		
		if( response.getStatus() >= 300 )
			throw new RuntimeException( response.getBody().toString());
		
		ObjectMapper mapper = new ObjectMapper();
		InstanceVultrList instanceList = mapper.readValue(bodyResponse, new TypeReference<InstanceVultrList>() {});
		
		List<InstanceResponse> instancesResponse = new ArrayList<>();
		
		for (InstanceVultr instances : instanceList.getInstances() ) {
			
			InstanceEntity instanceEntity = instanceRepository.findByInstanceId( String.valueOf(instances.getId() ) );
			
			if( instanceEntity != null ) {
				
				instances.setDatabase(instanceEntity);
				
			}else {
				instanceEntity = new InstanceOtherEntity();
				instanceEntity.setInstanceId( instances.getId() );
				instanceEntity.setName(instances.getLabel());
				instanceEntity.setMainIp(instances.getMain_ip());
				InstanceEntity newInstanceEntity = instanceRepository.save(instanceEntity);
				instances.setDatabase(newInstanceEntity);
			}
			
			instancesResponse.add(instances);
			
		}
		
		return instancesResponse;
	}
	
	private HttpResponse<JsonNode> getInstancesHttp() throws UnirestException {
		

		/*
		Unirest.config()
			.proxy("192.186.171.156", 4444, "dcdc79b36d", "Bgv2tnZQ")
			.connectTimeout(10000);
		*/
		
		HttpResponse<JsonNode> response = Unirest.get("https://api.vultr.com/v2/instances")
				.header("Authorization", "Bearer "+this.account.getToken() ) //+this.account.getToken())
				.header("Content-Type", "application/json")
				.asJson();

		
		return response ;

	}


	@Override
	public List<InstanceResponse> AddInstances ( AddInstanceRequest instanceRequest )
			throws JsonProcessingException, UnirestException {
		
		
		List<String> names  = this.generateName( instanceRequest.getName() , instanceRequest.getNumberInstances() );
		
		
		
		
		//System.out.println( names );
		
		/*
		for (InstanceResponse instancesRes : listInstances) {

			DropletInstance instances = (DropletInstance ) instancesRes ;
			
			InstanceEntity instanceEntity  = new InstanceOtherEntity();
			instanceEntity.setInstanceId(instances.getId()+"");
			instanceEntity.setName(instances.getName());
			if( instances.getNetworks().getV4().size() > 1 )
			instanceEntity.setMainIp( instances.getNetworks().getV4().get(1).getIp_address() );
			instanceEntity.setVmtaDomain(instanceRequest.getVmtaDomain());
			InstanceEntity newInstanceEntity = instanceRepository.save(instanceEntity);
			instances.setDatabase(newInstanceEntity);
			
		}
		 
		*/
		return null;
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
		return names ;

	}



}
