package com.alatamli.web.controllers;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alatamli.web.services.AccountService;
import com.alatamli.web.services.CronService;
import com.alatamli.web.services.InstancesService;
import com.alatamli.web.services.ProviderService;
import com.alatamli.web.services.UserService;

@RestController
@RequestMapping("/home")
public class HomeController {

	@Autowired
	InstancesService instancesService;
	
	
	@Autowired
	AccountService accountService;
	
	
	@Autowired
	ProviderService providerService;
	
	@Autowired
	CronService cronService;
	
	@Autowired 
	UserService userService;
	
	
	@GetMapping("/createdbyday")
	public ResponseEntity<List<Object>> home() {

		List<Object> states = instancesService.numberInstancesByDay();
		
		return new ResponseEntity<List<Object>>( states , HttpStatus.OK);
	}
	
	@GetMapping("/numberAccounts")
	public ResponseEntity<List<Object>> getNumberAccountByProvider( ){
		
		List<Object> numbers = accountService.getNumberAccountsByProvider();
		
		return new ResponseEntity<List<Object>>( numbers , HttpStatus.OK);
	}
	
	@GetMapping("/instancesByAccount")
	public ResponseEntity<List<Object>> getNumberInstanceByAccount( ){
		
		List<Object> instanceByAccount = accountService.getNumberInstanceByAccount();
		
		return new ResponseEntity<List<Object>>( instanceByAccount , HttpStatus.OK);
	}
	
	
	@GetMapping("/topDashInfos")
	public ResponseEntity<HashMap<String, Object>> getNumberProviderOfMonth( Principal principal){
		
		Object providerOfMonth = providerService.getNumberProviderOfMonth();
		Object numberInstancesOfMonth = instancesService.numberInstancesOfMonth( );
		Object numberAccountsOfEntity = accountService.numberAccountsOfEntity( principal.getName() );
		Object numberOfProvidersOfEntity = providerService.numberOfProvidersOfEntity( principal.getName() );
		Object numberOfTasks = cronService.numberOfTasks();
		Object numberOfUsersOfEntity = userService.numberOfUsersOfEntity( principal.getName() );
		
		HashMap<String, Object> response = new HashMap<String, Object>();	
		
		response.put("providerOfMonth", providerOfMonth);
		response.put("numberInstancesOfMonth", numberInstancesOfMonth);
		response.put("numberOfProvidersOfEntity", numberOfProvidersOfEntity);
		response.put("numberOfTasks", numberOfTasks);
		response.put("numberOfUsersOfEntity", numberOfUsersOfEntity);
		response.put("numberAccountsOfEntity", numberAccountsOfEntity);
		return new ResponseEntity<HashMap<String, Object>>( response , HttpStatus.OK);
		//return new ResponseEntity<List<Object>>( instanceByAccount , HttpStatus.OK);
	}
	
	
	
}
