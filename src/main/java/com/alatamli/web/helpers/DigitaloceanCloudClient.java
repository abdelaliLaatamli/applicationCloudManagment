package com.alatamli.web.helpers;


import java.util.List;

import com.alatamli.web.helpers.responses.DropletInstance;
import com.alatamli.web.helpers.responses.DropletsListResponse;
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
		//return retryHeader;
		
		//String retryHeader = response.getBody().toString();//.getHeaders().getFirst("Retry-After");
		//String retryHeader = response.getBody().toString();
				
		//return retryHeader;
	
	}

	@Override
	public HttpResponse<JsonNode> getInstancesHttp() throws UnirestException {
		String url = "https://api.digitalocean.com/v2/droplets" ;
		HttpResponse<JsonNode> response = Unirest.get(url)
				// .header("Authorization", "Bearer 19e2d322a12eb2722d8ba392edcb767a455160e2ca96307683581b4e2857add0")
				.header("Authorization", "Bearer "+this.account.getToken())
				.header("Content-Type", "application/json")
				.asJson();
		
		return response;
	}
	
	/*
	private JsonNode fetchJson(HttpResponse<JsonNode> response) throws UnirestException, InterruptedException {
		
	
		String retryHeader = response.getHeaders().getFirst("Retry-After");

		if (response.getStatus() == 200) {
			return response.getBody();
		}else {
			throw new IllegalArgumentException("No data get ");
		}
	}
	*/
	

	
	
}
