package com.alatamli.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alatamli.web.helpers.responses.DropletInstance;
import com.alatamli.web.services.InstancesService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mashape.unirest.http.exceptions.UnirestException;

@RestController
@RequestMapping("/instances")
public class InstanceController {
	
	@Autowired
	InstancesService instancesService;
	
	@GetMapping("/{accountId}")
	public ResponseEntity<List<DropletInstance>>  getInstance( @PathVariable long accountId ) throws JsonMappingException, JsonProcessingException, UnirestException {
		
		
		List<DropletInstance>  a = instancesService.getInstances( accountId );
		
		return new ResponseEntity<List<DropletInstance>>( a , HttpStatus.OK ) ;
		
	}
	
	

}
