package com.alatamli.web.services;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alatamli.web.entities.EntityEntity;
import com.alatamli.web.repositories.EntityRepository;
import com.alatamli.web.shared.dto.EntityDto;

@Service
public class EntityService {
	
	
	@Autowired
	EntityRepository entityRepository;
	
	@Autowired
	ModelMapper modelMapper;

	public List<EntityDto> getEntities() {

		List<EntityEntity> entitiesEntity = entityRepository.findAll();
		
		Type listType  = new TypeToken<List<EntityDto>>() {}.getType(); 
		List<EntityDto> entitiesDto = modelMapper.map( entitiesEntity , listType);
		
		return entitiesDto;
		
	}
	
	
	

}
