package com.alatamli.web.controllers;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alatamli.web.services.InstancesService;
import com.alatamli.web.services.UserService;

@RestController
@RequestMapping("/statistiques")
public class StatisquesController {
	
	@Autowired
	UserService userService;
	
	
	@Autowired 
	InstancesService instancesService;
	
	@Autowired
	ModelMapper modelMapper;
	
	@GetMapping("ofusers")
	private ResponseEntity<List<Object>> getUsersStatisques( Principal principal ){
		
		List<Object> userstatisques = userService.getStatistequesOfUsers(principal.getName());
		
		return new ResponseEntity<List<Object>>( userstatisques , HttpStatus.OK);
	}
	
	
	@GetMapping("numberInstances")
	private ResponseEntity<List<Object>> getnumberInstancesOfProvider( Principal principal ){
		
		List<Object> instancesStatisques = instancesService.getnumberInstancesOfProvider(principal.getName());
		
		return new ResponseEntity<List<Object>>( instancesStatisques , HttpStatus.OK);
	}
	
	
	@GetMapping("numberInstancesofentity")
	private ResponseEntity<HashMap<Integer,List<Object>>> getInstancesOfEntities( ){

		
		HashMap<Integer,List<Object>> instancesStatisques = new HashMap<Integer,List<Object>>();
		
		for( int i = 1 ; i <= 12 ; i++ ) {
			
			List<Object> instancesStatisques1 = instancesService.getInstancesOfEntities( i );
			instancesStatisques.put(i, instancesStatisques1);

		}
			
		
		return new ResponseEntity<HashMap<Integer,List<Object>>>( instancesStatisques , HttpStatus.OK);
	}
	
	@GetMapping( "stateOfEntities" )
	private ResponseEntity<HashMap<Integer,List<Object>>> stateOfEntities(){
		
		HashMap<Integer,List<Object>> instancesStatisques = new HashMap<Integer,List<Object>>();
		
		for( int i = 1 ; i <= 12 ; i++ ) {
			
			List<Object> instancesStatisquesOfMonth = instancesService.getStateOfEntities( i );
			instancesStatisques.put(i, instancesStatisquesOfMonth);

		}
		
		return new ResponseEntity<HashMap<Integer,List<Object>>>( instancesStatisques , HttpStatus.OK);
	}
	 
	
}
