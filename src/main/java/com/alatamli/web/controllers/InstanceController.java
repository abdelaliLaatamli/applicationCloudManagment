package com.alatamli.web.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alatamli.web.helpers.responses.DropletInstance;
import com.alatamli.web.requests.AddInstanceRequest;
import com.alatamli.web.requests.InstanceRequest;
import com.alatamli.web.services.InstancesService;
import com.alatamli.web.shared.dto.InstanceDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mashape.unirest.http.exceptions.UnirestException;

@RestController
@RequestMapping("/instances")
public class InstanceController {
	
	@Autowired
	InstancesService instancesService;
	
	@Autowired
	ModelMapper modelMapper;
	
	@GetMapping("/{accountId}")
	public ResponseEntity<List<DropletInstance>>  getInstance( @PathVariable long accountId ) throws JsonMappingException, JsonProcessingException, UnirestException {
		
		
		List<DropletInstance> instances = instancesService.getInstances( accountId );
		
		return new ResponseEntity<List<DropletInstance>>( instances , HttpStatus.OK ) ;
		
	}
	
	@PutMapping("/update/{type}/{instanceId}")
	public ResponseEntity<InstanceDto> updateInstance( @PathVariable String type , @PathVariable String instanceId , @RequestBody InstanceRequest instanceRequest ){
		
		InstanceDto instance = modelMapper.map( instanceRequest , InstanceDto.class);
		
		InstanceDto newinstance = instancesService.updateInstance( type , instanceId , instance );
		
		
		return new ResponseEntity<InstanceDto>( newinstance , HttpStatus.ACCEPTED  );
	}
	
	
	@PutMapping("/options/{accountId}/{instanceId}/{option}")
	public ResponseEntity<Object> updateOptions( @PathVariable long accountId , @PathVariable String instanceId , @PathVariable String  option ) throws UnirestException{
		
		instancesService.updateOptions(accountId , instanceId , option);
		
		return new ResponseEntity<>( HttpStatus.ACCEPTED );
	}
	
	
	@PostMapping("/{accountId}")
	public ResponseEntity<List<DropletInstance>> addInstance( @PathVariable long accountId , @RequestBody AddInstanceRequest instanceRequest ) throws JsonProcessingException, UnirestException{
		
		List<DropletInstance> instance = instancesService.addInstance(accountId , instanceRequest);
	
		return new ResponseEntity<List<DropletInstance>>( instance , HttpStatus.CREATED ) ;
	}
	
	
	@DeleteMapping( "/{accountId}/{instanceId}" )
	public ResponseEntity<Object> deleteInstance( @PathVariable long accountId , @PathVariable String instanceId ) throws UnirestException{
		
		instancesService.deleteInstance(accountId , instanceId);
		
		
		return new ResponseEntity<> ( HttpStatus.NO_CONTENT );
	}
	
	

}
