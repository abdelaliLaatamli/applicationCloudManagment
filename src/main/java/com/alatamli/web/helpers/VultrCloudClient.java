package com.alatamli.web.helpers;

import java.util.List;

import com.alatamli.web.helpers.responses.InstanceResponse;
import com.alatamli.web.repositories.InstanceRepository;
import com.alatamli.web.shared.dto.AccountOneKeyDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mashape.unirest.http.exceptions.UnirestException;

public class VultrCloudClient implements ICloudClient {

	InstanceRepository instanceRepository;
	public AccountOneKeyDto account ;
	
	public VultrCloudClient( AccountOneKeyDto account , InstanceRepository instanceRepository  ) {
		this.account = account ;
		this.instanceRepository = instanceRepository;
	}
	
	
	@Override
	public List<InstanceResponse> getInstances()
			throws UnirestException, JsonMappingException, JsonProcessingException {
		
		return null;
	}

}
