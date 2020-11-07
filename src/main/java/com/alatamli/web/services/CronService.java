package com.alatamli.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alatamli.web.repositories.CronRepository;

@Service
public class CronService {
	
	@Autowired
	CronRepository cronRepository;

	public Object numberOfTasks() {

		Object numberOfTasks = cronRepository.numberOfTasks();
		
		
		return numberOfTasks;
	}

}
