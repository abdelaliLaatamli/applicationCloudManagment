package com.alatamli.web.controllers;

import java.security.Principal;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alatamli.web.services.UserService;

@RestController
@RequestMapping("/statistiques")
public class StatisquesController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	ModelMapper modelMapper;
	
	@GetMapping("ofusers")
	private ResponseEntity<List<Object>> getUsersStatisques( Principal principal ){
		
		List<Object> userstatisques = userService.getStatistequesOfUsers(principal.getName());
		
		return new ResponseEntity<List<Object>>( userstatisques , HttpStatus.OK);
	}
	
	
}
