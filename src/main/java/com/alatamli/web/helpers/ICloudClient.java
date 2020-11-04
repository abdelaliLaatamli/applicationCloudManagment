package com.alatamli.web.helpers;

import java.util.List;

import com.alatamli.web.helpers.responses.InstanceResponse;
import com.alatamli.web.requests.AddInstanceRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mashape.unirest.http.exceptions.UnirestException;


public interface ICloudClient {
	
	/*
	HttpResponse<JsonNode> getInstancesHttp() throws UnirestException; 
	HttpResponse<JsonNode> addInstancesHttp(String request) throws UnirestException;
	HttpResponse<JsonNode> deleteInstancesHttp(String instanceId) throws UnirestException;
	HttpResponse<JsonNode> updateOptionsHttp(String request , String instanceId) throws UnirestException;
	*/
	List<InstanceResponse> getInstances() throws UnirestException, JsonMappingException, JsonProcessingException ;
	List<InstanceResponse> AddInstances(AddInstanceRequest instanceRequest) throws JsonProcessingException, UnirestException ;
}
