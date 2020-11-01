package com.alatamli.web.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alatamli.web.entities.AccountOneKeyEntity;
import com.alatamli.web.entities.InstanceEntity;
import com.alatamli.web.entities.InstanceOtherEntity;
import com.alatamli.web.helpers.DigitaloceanCloudClient;
import com.alatamli.web.helpers.responses.DropletInstance;
import com.alatamli.web.repositories.AccountRepository;
import com.alatamli.web.repositories.InstanceRepository;
import com.alatamli.web.shared.dto.AccountOneKeyDto;
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
	
	public List<DropletInstance> getInstances(long accountId) throws JsonMappingException, JsonProcessingException, UnirestException {

		AccountOneKeyEntity account = (AccountOneKeyEntity) accountRepository.findById(accountId)
																				.orElseThrow( () -> new IllegalArgumentException("there is no Account by this id " + accountId)  ); 
		
		AccountOneKeyDto accountDto = modelMapper.map(account, AccountOneKeyDto.class);
		
		DigitaloceanCloudClient digitalClient= new DigitaloceanCloudClient(accountDto);
	
		List<DropletInstance> listInstances = digitalClient.getInstances();
		
		for (DropletInstance instances : listInstances) {
			
			InstanceEntity instanceEntity = instanceRepository.findByInstanceId(instances.getId()+"");
			
			if( instanceEntity != null ) {
				
				instances.setDatabase(instanceEntity);
				
			}else {
				instanceEntity = new InstanceOtherEntity();
				instanceEntity.setInstanceId(instances.getId()+"");
				instanceEntity.setName(instances.getName());
				instanceEntity.setMainIp(instances.getNetworks().getV4().get(1).getIp_address());
				InstanceEntity newInstanceEntity = instanceRepository.save(instanceEntity);
				instances.setDatabase(newInstanceEntity);
			}
			
		}
		
		
		
		return listInstances;
	
			
		
	}
	
	
	

}
