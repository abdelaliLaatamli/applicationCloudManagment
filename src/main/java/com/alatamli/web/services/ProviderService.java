package com.alatamli.web.services;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alatamli.web.entities.ProviderEntity;
import com.alatamli.web.entities.UserEntity;
import com.alatamli.web.repositories.ProviderRepository;
import com.alatamli.web.repositories.UserRepository;
import com.alatamli.web.requests.ProviderRequest;
import com.alatamli.web.shared.dto.ProviderDto;

@Service
public class ProviderService {
	
	@Autowired 
	UserRepository userRepository;
	
	@Autowired
	ProviderRepository providerRepository;
	
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

	public ProviderDto addProvider(ProviderRequest providerRequest) {
		
		ProviderEntity providerEntity = modelMapper.map(providerRequest, ProviderEntity.class);
		
		ProviderEntity newProvider = providerRepository.save(providerEntity);
		
		ProviderDto providerDto = modelMapper.map(newProvider, ProviderDto.class);
		
		return providerDto;
	}
	
	
	
	

}
