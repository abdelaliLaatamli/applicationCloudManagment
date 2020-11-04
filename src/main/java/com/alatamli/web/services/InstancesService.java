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
import com.alatamli.web.helpers.responses.InstanceResponse;
import com.alatamli.web.helpers.responses.digitalocean.DropletInstance;
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
	
	public List<InstanceResponse> getInstances(long accountId) throws JsonMappingException, JsonProcessingException, UnirestException {

		AccountOneKeyEntity account = (AccountOneKeyEntity) accountRepository.findById(accountId)
																				.orElseThrow( () -> new IllegalArgumentException("there is no Account by this id " + accountId)  ); 
		
		AccountOneKeyDto accountDto = modelMapper.map(account, AccountOneKeyDto.class);
		
		DigitaloceanCloudClient digitalClient= new DigitaloceanCloudClient(accountDto);
	
		List<InstanceResponse> listInstances = digitalClient.getInstances();
		
		for (InstanceResponse instancesRes : listInstances) {
			
			DropletInstance instances = (DropletInstance) instancesRes ;
			
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

	public List<InstanceResponse> addInstance(long accountId, AddInstanceRequest instanceRequest) throws JsonProcessingException, UnirestException {
		
		AccountOneKeyEntity account = (AccountOneKeyEntity) accountRepository.findById(accountId)
				.orElseThrow( () -> new IllegalArgumentException("there is no Account by this id " + accountId)  ); 

		AccountOneKeyDto accountDto = modelMapper.map(account, AccountOneKeyDto.class);
		
		DigitaloceanCloudClient digitalClient = new DigitaloceanCloudClient(accountDto);
		
		List<InstanceResponse> listInstances  = digitalClient.AddInstances( instanceRequest );
		
		
		for (InstanceResponse instancesRes : listInstances) {

			DropletInstance instances = (DropletInstance ) instancesRes ;
			
			InstanceEntity instanceEntity  = new InstanceOtherEntity();
			instanceEntity.setInstanceId(instances.getId()+"");
			instanceEntity.setName(instances.getName());
			if( instances.getNetworks().getV4().size() > 1 )
			instanceEntity.setMainIp( instances.getNetworks().getV4().get(1).getIp_address() );
			instanceEntity.setVmtaDomain(instanceRequest.getVmtaDomain());
			InstanceEntity newInstanceEntity = instanceRepository.save(instanceEntity);
			instances.setDatabase(newInstanceEntity);
			
		}
		
		return listInstances;
		
	}

	public void deleteInstance(long accountId, String instanceId) throws UnirestException {
		
		AccountOneKeyEntity account = (AccountOneKeyEntity) accountRepository.findById(accountId)
				.orElseThrow( () -> new IllegalArgumentException("there is no Account by this id " + accountId)  ); 

		AccountOneKeyDto accountDto = modelMapper.map(account, AccountOneKeyDto.class);
		
		DigitaloceanCloudClient digitalClient = new DigitaloceanCloudClient(accountDto);
		
		digitalClient.deleteInstance( instanceId );
		
		InstanceEntity instanceEntity = instanceRepository.findByInstanceId(instanceId);
		
		if( instanceEntity != null ) {
			
			//instanceEntity = new InstanceOtherEntity();
			instanceEntity.setDeleted(true);
			instanceEntity.setDeletedAt(Instant.now());
			instanceRepository.save(instanceEntity);
			
		}
			
	}

	public void updateOptions(long accountId, String instanceId, String option) throws UnirestException {
		
		AccountOneKeyEntity account = (AccountOneKeyEntity) accountRepository.findById(accountId)
				.orElseThrow( () -> new IllegalArgumentException("there is no Account by this id " + accountId)  ); 

		AccountOneKeyDto accountDto = modelMapper.map(account, AccountOneKeyDto.class);
		
		DigitaloceanCloudClient digitalClient = new DigitaloceanCloudClient(accountDto);
		
		digitalClient.updateOption( instanceId , option );
		
	}
	
	
	

}
