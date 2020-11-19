package com.alatamli.web.services;

import java.lang.reflect.Type;
import java.time.Instant;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.alatamli.web.entities.CronEntity;
import com.alatamli.web.entities.InstanceEntity;
import com.alatamli.web.helpers.DigitaloceanCloudClient;
import com.alatamli.web.helpers.ICloudClient;
import com.alatamli.web.helpers.VultrCloudClient;
import com.alatamli.web.repositories.AccountRepository;
import com.alatamli.web.repositories.CronRepository;
import com.alatamli.web.repositories.InstanceRepository;
import com.alatamli.web.requests.TaskRequestAction;
import com.alatamli.web.shared.dto.AccountOneKeyDto;
import com.alatamli.web.shared.dto.TaskDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mashape.unirest.http.exceptions.UnirestException;

@Service
public class TaskService {
	
	@Autowired
	CronRepository cronRepository;
	
	@Autowired
	InstanceRepository instanceRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired 
	ModelMapper modelMapper;

	public List<TaskDto> getListTasks(String email) {

		List<CronEntity> listEntity = cronRepository.findAll();
		
		Type listType  = new TypeToken<List<TaskDto>>() {}.getType(); 
		List<TaskDto> tasksDtos = modelMapper.map( listEntity , listType); 
		
		return tasksDtos;
	}

	public TaskDto storeTask(TaskDto taskDto, long instanceId) {
		
		CronEntity cronEntity = modelMapper.map(taskDto, CronEntity.class);
		
		InstanceEntity instanceEntity = instanceRepository.findById( instanceId ).orElse(null);
		
		cronEntity.setCreatedAt(Instant.now());
		cronEntity.setUpdatedAt(Instant.now());
		cronEntity.setInstance(instanceEntity);
		
		CronEntity newCronEntity = cronRepository.save(cronEntity);
		
		TaskDto newCronDto = modelMapper.map(newCronEntity, TaskDto.class);
		
		return newCronDto;
	}

	public void deleteTask(long taskId) {
		
		CronEntity cronEntity = cronRepository.findById(taskId)
									.orElseThrow(() -> new IllegalArgumentException("there is no Task by this id"));
		
		cronRepository.delete(cronEntity);
		
	}

	public TaskDto updateTask(long taskId, TaskRequestAction taskRequestAction) {
		
		CronEntity cronEntity = cronRepository.findById(taskId)
				.orElseThrow(() -> new IllegalArgumentException("there is no Task by this id"));
		
		if( taskRequestAction.getAction().equals("start" ) )
			cronEntity.setStoped(false);
		else if( taskRequestAction.getAction().equals("stop") )
			cronEntity.setStoped(true);
		
		CronEntity newCronEntity = cronRepository.save(cronEntity);
		
		TaskDto newCronDto = modelMapper.map(newCronEntity, TaskDto.class);
		
		return newCronDto;
		
	}

	public void taskExecuter() throws JsonMappingException, JsonProcessingException, UnirestException, InterruptedException {
		

		List<CronEntity> tasksToExecute = cronRepository.tasksToRun();
		
//		System.out.println( tasksToExecute );
		
		
		for (CronEntity cronEntity : tasksToExecute) {
			
			
			AccountOneKeyDto accountDto = modelMapper.map(cronEntity.getInstance().getAccount(), AccountOneKeyDto.class);
			
			ICloudClient cloudClient ;
			
			switch (accountDto.getProvider().getName()) {
				
				case "digitalocean":
					cloudClient = new DigitaloceanCloudClient(accountDto , instanceRepository , accountRepository , cronRepository);
					break;
				
				case "vultr" :
					cloudClient = new VultrCloudClient( accountDto , instanceRepository , accountRepository);
					break;
	
				default:
					throw new RuntimeException("This Provider not yet Supported");
			}
			
			//cloudClient.restartInstance( cronEntity.getInstance().getInstanceId() , cronEntity );
			

			cloudClient.restartInstance( cronEntity );
			
		}
		

		
	}


	
	
	
	

}
