package com.alatamli.web.services;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alatamli.web.entities.EntityEntity;
import com.alatamli.web.entities.ProviderEntity;
import com.alatamli.web.entities.UserEntity;
import com.alatamli.web.repositories.EntityRepository;
import com.alatamli.web.repositories.ProviderRepository;
import com.alatamli.web.repositories.UserRepository;
import com.alatamli.web.requests.ProviderActionsRequest;
import com.alatamli.web.shared.dto.ProviderDto;

@Service
public class ProviderService {
	
	@Autowired 
	UserRepository userRepository;
	
	@Autowired
	ProviderRepository providerRepository;
	
	@Autowired
	EntityRepository entityRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	
	public ProviderDto getProvider( long providerId ) {
		
		ProviderEntity providerEntity = providerRepository.findById(providerId)
											.orElseThrow(( ) -> new IllegalArgumentException("there is no provider of this id , please check id"));
		
		ProviderDto provider = modelMapper.map(providerEntity, ProviderDto.class);
		
		return provider;
	}

	public List<ProviderDto> getProviders(String email) {
		
		UserEntity user = userRepository.findByEmail(email);
		
		Set<ProviderEntity> providers = user.getEntity().getProviders();
		
		Type listType = new TypeToken<List<ProviderDto>>() {}.getType();
		List<ProviderDto> providersDto = modelMapper.map( providers , listType);
		
	
		return providersDto;
	}

	public ProviderDto addProvider(ProviderDto provider) {
		
		ProviderEntity providerEntity = modelMapper.map( provider , ProviderEntity.class);
		
		ProviderEntity newProvider = providerRepository.save(providerEntity);
		
		ProviderDto providerDto = modelMapper.map(newProvider, ProviderDto.class);
		
		return providerDto;
	}

	public ProviderDto updateProvider(long providerId, ProviderDto provider) {

		ProviderEntity providerEntity = providerRepository.findById(providerId)
				.orElseThrow(( ) -> new IllegalArgumentException("there is no provider of this id , please check id"));
		
		providerEntity.setName(provider.getName());
		providerEntity.setProviderKeysType(provider.getProviderKeysType());
		
		ProviderEntity updatedProvider = providerRepository.save( providerEntity ); 
		
		ProviderDto providerDto = modelMapper.map(updatedProvider, ProviderDto.class);
		
		return providerDto;
	}

	public ProviderDto attachProviderToEntity(long providerId, ProviderActionsRequest providerActionsRequest) {
		
		ProviderEntity providerEntity = providerRepository.findById(providerId)
				.orElseThrow(( ) -> new IllegalArgumentException("there is no provider of this id , please check id"));
		
		EntityEntity entityEntity = entityRepository.findById(providerActionsRequest.getEntityId())
				.orElseThrow(( ) -> new IllegalArgumentException("there is no provider of this id , please check id"));
		
		Set<EntityEntity> entitiesOfProvider = null ;
		ProviderEntity updatedProvider = null ;
		switch (providerActionsRequest.getAction()) {
			case "attach":
				
				entitiesOfProvider = providerEntity.getEntities();
				if( entitiesOfProvider.contains(entityEntity) ) throw new RuntimeException("Entity Already Attached");
				entityEntity.getProviders().add(providerEntity);
				entitiesOfProvider.add(entityEntity);
				providerEntity.setEntities(entitiesOfProvider);
				updatedProvider = providerRepository.save( providerEntity ); 
				
				break;
			case "detach" :
				entitiesOfProvider = providerEntity.getEntities();
				if( !entitiesOfProvider.contains(entityEntity) ) throw new RuntimeException("Entity not Attached to Account ") ;
				entityEntity.getProviders().remove(providerEntity);
				entitiesOfProvider.remove(entityEntity);
				providerEntity.setEntities(entitiesOfProvider);
			    updatedProvider = providerRepository.save( providerEntity ); 
				
				break;
	
			default:
				break;
		}
		ProviderDto providerDto = modelMapper.map(updatedProvider, ProviderDto.class);
		
		
		return providerDto;
		/*
		ProviderEntity providerEntity = providerRepository.findById(providerId)
				.orElseThrow(( ) -> new IllegalArgumentException("there is no provider of this id , please check id"));
		
		
		
		Set<EntityEntity> entities = providerEntity.getEntities();
		
		
		for (long entityId : entityList.getEntities() ) {
			
			EntityEntity entity = entityRepository.findById(entityId).orElse( null );
			
			if( entity == null ) continue;
			
			if( entities.contains(entity) ) continue;
			
			entities.add(entity);
			
		}
		
		providerEntity.setEntities( entities);
		
		ProviderEntity updatedProvider = providerRepository.save( providerEntity ); 
		
		ProviderDto providerDto = modelMapper.map(updatedProvider, ProviderDto.class);
		
		return providerDto;
		*/
	}

	public Object getNumberProviderOfMonth() {
		Object providerOfMonth =  providerRepository.getNumberProviderOfMonth();
		return providerOfMonth;
	}

	public Object numberOfProvidersOfEntity(String email) {
		
		UserEntity user = userRepository.findByEmail(email);
		Object numberOfProvidersOfEntity =  providerRepository.numberOfProvidersOfEntity( user.getId() );
		
		return numberOfProvidersOfEntity;
	}

	
	
	
	

}
