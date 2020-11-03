package com.alatamli.web.helpers;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;


public interface ICloudClient {
	
	HttpResponse<JsonNode> getInstancesHttp() throws UnirestException; 
	HttpResponse<JsonNode> addInstancesHttp(String request) throws UnirestException;
	HttpResponse<JsonNode> deleteInstancesHttp(String instanceId) throws UnirestException;
	HttpResponse<JsonNode> updateOptionsHttp(String request , String instanceId) throws UnirestException;
}
