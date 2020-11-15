package com.alatamli.web.controllers;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alatamli.web.helpers.responses.StatistiqueResponse;
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

		
		
		HashMap<Integer,List<Object>> instancesStatisques = new HashMap();
		
		//List<StatistiqueResponse> listObjects = new ArrayList<>();

		
		for( int i = 1 ; i <= 12 ; i++ ) {
			
			List<Object> instancesStatisques1 = instancesService.getInstancesOfEntities( i );
			
			instancesStatisques.put(i, instancesStatisques1);
			/*
			for( Object oos : instancesStatisques1 ) {
				
				Object [] ood =(Object [] ) oos; 
				
				StatistiqueResponse act = null ;
				
				for( StatistiqueResponse object : listObjects ) 
					if( object.getName() == ood[0] ) act = object ;
				
				if( act == null ) {
					act = new StatistiqueResponse();
					act.setName((String) ood[0] );
					List<Integer> aas = new ArrayList();
					aas.add( ((BigDecimal)  ood[1]).intValue() );
					act.setValues(aas);
					listObjects.add(act);
					
				}else {
					
					List<Integer> ooss = act.getValues();
					ooss.add(((BigDecimal)  ood[1]).intValue());
					act.setValues(ooss);
					
				}
				*/
				
			}
			
			/*
			for( StatistiqueResponse object : listObjects ) {
				
				StatistiqueResponse act ;
				
				for( Object oos : instancesStatisques1 ) {
					Object [] ood =(Object [] ) oos; 
					if( object.getName() == ood[0] ) {
						act = object ;
						List<Integer> ooss = act.getValues();
						ooss.add((int)ood[1]);
						act.setValues(ooss);
						
					}else {
						
						act = new StatistiqueResponse();
						act.setName((String) ood[0] );
						List<Integer> aas = new ArrayList();
						aas.add( (int) ood[0] );
						act.setValues(aas);
						listObjects.add(act);
					}
				}
				// if( object.getName() == instancesStatisques1. )
				
				
			}
			*/
			
			/*
			StatistiqueResponse o = new StatistiqueResponse();
			o.setName( instancesStatisques1.get(0).toString() );
			List<Integer> aas = o.getValues();
			aas.add( (int) instancesStatisques1.get(1) );
			o.setValues(aas);
			*/
			
		//}
		
		return new ResponseEntity<HashMap<Integer,List<Object>>>( instancesStatisques , HttpStatus.OK);
	}
	
	
}
