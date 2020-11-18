package com.alatamli.web.controllers;

import java.lang.reflect.Type;
import java.security.Principal;
import java.util.List;


import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alatamli.web.requests.TaskRequest;
import com.alatamli.web.requests.TaskRequestAction;
import com.alatamli.web.responses.TaskResponse;
import com.alatamli.web.services.TaskService;
import com.alatamli.web.shared.dto.TaskDto;

@RestController
@RequestMapping("tasks")
public class TasksController {
	
	@Autowired
	TaskService taskService;
	
	
	@Autowired
	ModelMapper modelMapper;
	
	@GetMapping("/{accountId}")
	private ResponseEntity<List<TaskResponse>> getTasks(  @PathVariable long accountId , Principal principal ) {
	
		List<TaskDto> tasks = taskService.getListTasks( principal.getName() );
		
		Type listType  = new TypeToken<List<TaskResponse>>() {}.getType(); 
		List<TaskResponse> tasksResponses = modelMapper.map( tasks , listType);
		
		return new ResponseEntity<List<TaskResponse>>( tasksResponses , HttpStatus.OK );
	}
	
	@PostMapping
	private ResponseEntity<TaskResponse> storeTasks( @RequestBody TaskRequest taskRequest ) {
	
		TaskDto taskDto = modelMapper.map(taskRequest, TaskDto.class);
		
		TaskDto task = taskService.storeTask( taskDto , taskRequest.getInstance() );
		
		TaskResponse taskResponse = modelMapper.map(task, TaskResponse.class );
		
		return new ResponseEntity<TaskResponse>( taskResponse , HttpStatus.CREATED );
	}
	
	
	@PutMapping("action/{taskId}" )
	private ResponseEntity<TaskResponse> updateAction(@PathVariable long taskId , @RequestBody TaskRequestAction taskRequestAction  ){
			
		TaskDto task = taskService.updateTask( taskId , taskRequestAction );
			
		TaskResponse taskResponse = modelMapper.map(task, TaskResponse.class );
		
		return new ResponseEntity<TaskResponse> ( taskResponse , HttpStatus.ACCEPTED );
	}
	
	
	@DeleteMapping("/{taskId}")
	private ResponseEntity<Object> deleteInstance( @PathVariable long taskId ){
		
		taskService.deleteTask( taskId );
		
		return new ResponseEntity<Object>( HttpStatus.NO_CONTENT );
	}

	
	
	@GetMapping("/test")
	private void testCron( ) {
		
		taskService.taskExecuter();
		
		
	}
}
