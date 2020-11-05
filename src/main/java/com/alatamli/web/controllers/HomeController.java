package com.alatamli.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alatamli.web.services.AccountService;
import com.alatamli.web.services.InstancesService;

@RestController
@RequestMapping("/home")
public class HomeController {

	@Autowired
	InstancesService instancesService;
	
	
	@Autowired
	AccountService accountService;
	
	
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
	
	
}
