package com.alatamli.web.services;

import java.time.Instant;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alatamli.web.entities.AccountOneKeyEntity;
import com.alatamli.web.entities.InstanceEntity;
import com.alatamli.web.entities.InstanceOtherEntity;
import com.alatamli.web.helpers.DigitaloceanCloudClient;
import com.alatamli.web.helpers.ICloudClient;
import com.alatamli.web.helpers.VultrCloudClient;
import com.alatamli.web.helpers.responses.InstanceResponseHttp;
import com.alatamli.web.repositories.AccountRepository;
import com.alatamli.web.repositories.InstanceRepository;
import com.alatamli.web.requests.AddInstanceRequest;
import com.alatamli.web.shared.dto.AccountOneKeyDto;
import com.alatamli.web.shared.dto.InstanceDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mashape.unirest.http.exceptions.UnirestException;

@Service
public class InstancesService {

	@Autowired
	AccountRepository accountRepository;
	
	
	@Autowired
	InstanceRepository instanceRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	public List<InstanceResponseHttp> getInstances(long accountId) throws JsonMappingException, JsonProcessingException, UnirestException {

		AccountOneKeyEntity account = (AccountOneKeyEntity) accountRepository.findById(accountId)
																				.orElseThrow( () -> new IllegalArgumentException("there is no Account by this id " + accountId)  ); 
		
		AccountOneKeyDto accountDto = modelMapper.map(account, AccountOneKeyDto.class);
		
		ICloudClient cloudClient ;
		
		switch (accountDto.getProvider().getName()) {
			case "digitalocean":
				cloudClient = new DigitaloceanCloudClient(accountDto , instanceRepository , accountRepository);
				break;
				
			case "vultr" :
				cloudClient = new VultrCloudClient(accountDto , instanceRepository , accountRepository );
				break;
	
			default:
				throw new RuntimeException("This Provider not yet Supported");
		}
		
		
		
		List<InstanceResponseHttp> listInstances = cloudClient.getInstances();
		
		return listInstances;
	
	}

	public InstanceDto updateInstance(String type, String instanceId, InstanceDto instance) {

		InstanceEntity instanceEntity = instanceRepository.findByInstanceId( instanceId );
		
		InstanceDto newInstance;
		InstanceEntity newInstanceEntity;
		
		if( instanceEntity == null ) {
			
			instanceEntity = new InstanceOtherEntity();
			instanceEntity.setInstanceId(instanceId);
			instanceEntity.setName(instance.getName());
			instanceEntity.setMainIp(instance.getMainIp());
			instanceEntity.setVmtaDomain(instance.getVmtaDomain());
			
		}
		
		switch (type) {
		
			case "vmta":
				
				instanceEntity.setVmtaDomain(instance.getVmtaDomain());
				newInstanceEntity = instanceRepository.save(instanceEntity);
				
				break;
				
			case "install":
				instanceEntity.setInstalled(true);
				newInstanceEntity = instanceRepository.save(instanceEntity);
				
				break;
				
			default:
				
				newInstanceEntity = instanceRepository.save(instanceEntity);
				
				break;
				
		}
		
		
		newInstance = modelMapper.map(newInstanceEntity, InstanceDto.class);
		
		return newInstance;
		
	}

	public List<InstanceResponseHttp> addInstance(long accountId, AddInstanceRequest instanceRequest) throws JsonProcessingException, UnirestException {
		
		AccountOneKeyEntity account = (AccountOneKeyEntity) accountRepository.findById(accountId)
				.orElseThrow( () -> new IllegalArgumentException("there is no Account by this id " + accountId)  ); 

		AccountOneKeyDto accountDto = modelMapper.map(account, AccountOneKeyDto.class);
		
		ICloudClient cloudClient ;
		
		
		switch (accountDto.getProvider().getName()) {
		
			case "digitalocean":
				cloudClient = new DigitaloceanCloudClient(accountDto , instanceRepository , accountRepository);
				break;
			
			case "vultr" :
				cloudClient = new VultrCloudClient(accountDto , instanceRepository , accountRepository);
				break;

			default:
				throw new RuntimeException("This Provider not yet Supported");
		}
		
		
		List<InstanceResponseHttp> listInstances = cloudClient.AddInstances(instanceRequest);
		
		
		return listInstances;
		
	}

	public void deleteInstance(long accountId, String instanceId) throws UnirestException {
		
		AccountOneKeyEntity account = (AccountOneKeyEntity) accountRepository.findById(accountId)
				.orElseThrow( () -> new IllegalArgumentException("there is no Account by this id " + accountId)  ); 

		AccountOneKeyDto accountDto = modelMapper.map(account, AccountOneKeyDto.class);
		
		
		ICloudClient cloudClient ;
		
		
		switch (accountDto.getProvider().getName()) {
		
			case "digitalocean":
				cloudClient = new DigitaloceanCloudClient(accountDto , instanceRepository , accountRepository );
				break;
			
			case "vultr" :
				cloudClient = new VultrCloudClient(accountDto , instanceRepository , accountRepository );
				break;

			default:
				throw new RuntimeException("This Provider not yet Supported");
		}
		
		cloudClient.deleteInstance( instanceId );
		
		InstanceEntity instanceEntity = instanceRepository.findByInstanceId(instanceId);
		
		if( instanceEntity != null ) {
			
			instanceEntity.setDeleted(true);
			instanceEntity.setDeletedAt(Instant.now());
			instanceRepository.save(instanceEntity);
			
		}
			
	}

	public void updateOptions(long accountId, String instanceId, String option) throws UnirestException {
		
		AccountOneKeyEntity account = (AccountOneKeyEntity) accountRepository.findById(accountId)
				.orElseThrow( () -> new IllegalArgumentException("there is no Account by this id " + accountId)  ); 

		AccountOneKeyDto accountDto = modelMapper.map(account, AccountOneKeyDto.class);
		
		ICloudClient cloudClient ;
		
		
		switch (accountDto.getProvider().getName()) {
		
			case "digitalocean":
				cloudClient = new DigitaloceanCloudClient(accountDto , instanceRepository , accountRepository );
				break;
			
			case "vultr" :
				cloudClient = new VultrCloudClient(accountDto , instanceRepository , accountRepository );
				break;

			default:
				throw new RuntimeException("This Provider not yet Supported");
		}
		
		cloudClient.updateOption( instanceId , option );
		
	}
	
	
	public List<Object> numberInstancesByDay() {
		
		List<Object> states = instanceRepository.getStatistiquesByDay();
		
		return states;
		
	}

	public Object numberInstancesOfMonth() {
		Object numberInstancesOfMonth = instanceRepository.numberInstancesOfMonth();
		return numberInstancesOfMonth;
	}


	
	
	

}
