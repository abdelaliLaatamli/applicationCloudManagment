package com.alatamli.web.helpers;

import java.util.List;

import com.alatamli.web.entities.CronEntity;
import com.alatamli.web.helpers.responses.InstanceResponseHttp;
import com.alatamli.web.requests.AddInstanceRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mashape.unirest.http.exceptions.UnirestException;


public interface ICloudClient {
	
	List<InstanceResponseHttp> getInstances() throws UnirestException, JsonMappingException, JsonProcessingException ;
	List<InstanceResponseHttp> AddInstances(AddInstanceRequest instanceRequest) throws JsonProcessingException, UnirestException ;
	void deleteInstance(String instanceId) throws UnirestException;
	void updateOption(String instanceId, String option) throws UnirestException;
	void restartInstance( CronEntity taskEntity) throws UnirestException, JsonMappingException, JsonProcessingException, InterruptedException;
}
