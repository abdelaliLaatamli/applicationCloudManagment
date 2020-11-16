package com.alatamli.web.services;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import com.alatamli.web.entities.CronEntity;
import com.alatamli.web.repositories.CronRepository;
import com.alatamli.web.shared.dto.TaskDto;

@Service
public class TaskService {
	
	@Autowired
	CronRepository cronRepository;
	
	@Autowired 
	ModelMapper modelMapper;

	public List<TaskDto> getListTasks(String email) {

		List<CronEntity> listEntity = cronRepository.findAll();
		
		Type listType  = new TypeToken<List<TaskDto>>() {}.getType(); 
		List<TaskDto> tasksDtos = modelMapper.map( listEntity , listType); 
		
		return tasksDtos;
	}

	public TaskDto storeTask(TaskDto taskDto) {
		
		
		CronEntity cronEntity = modelMapper.map(taskDto, CronEntity.class);
		
		CronEntity newCronEntity = cronRepository.save(cronEntity);
		
		TaskDto newCronDto = modelMapper.map(newCronEntity, TaskDto.class);
		
		return newCronDto;
	}
	
	
	

}
