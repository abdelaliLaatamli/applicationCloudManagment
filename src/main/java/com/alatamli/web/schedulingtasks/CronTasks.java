package com.alatamli.web.schedulingtasks;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alatamli.web.entities.CronEntity;
import com.alatamli.web.services.TaskService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mashape.unirest.http.exceptions.UnirestException;

@Component
public class CronTasks {
	
	@Autowired
	TaskService taskService;
	
	@Scheduled( fixedDelay = 60000 )
	
	public void reportCurrentTime() throws JsonMappingException, JsonProcessingException, UnirestException, InterruptedException {

		
		List<CronEntity> tasksToRun = taskService.getTasksToRun();
		
		
		for (CronEntity taskToRun : tasksToRun) {
			
			taskService.runTask(taskToRun);
			
		}
		
		System.out.println(  "The time is now "+(new SimpleDateFormat("HH:mm:ss")).format(new Date())  );
	}

}
