package com.alatamli.web.controllers;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alatamli.web.responses.EntityResponse;
import com.alatamli.web.services.EntityService;
import com.alatamli.web.shared.dto.EntityDto;

@RestController
@RequestMapping("entities")
public class EntityController {

	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	EntityService entityService;
	
	@GetMapping
	private ResponseEntity<List<EntityResponse>> getEntities( ){
		
		List<EntityDto> accountsDto = entityService.getEntities();
		
		
		Type listType  = new TypeToken<List<EntityResponse>>() {}.getType(); 
		List<EntityResponse> entitiesResponse = modelMapper.map( accountsDto , listType);
		
		return new ResponseEntity<List<EntityResponse>>( entitiesResponse , HttpStatus.OK );
	}
	
	
	
	
}
