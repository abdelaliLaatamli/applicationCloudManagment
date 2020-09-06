package com.alatamli.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alatamli.web.requests.UserRequest;
import com.alatamli.web.responces.UserResponce;
import com.alatamli.web.services.AuthService;
import com.alatamli.web.shared.dto.UserDto;

@RestController
@RequestMapping("/users")
public class UserContoller {
	
	
	@Autowired
	AuthService authService;
	
	@Autowired
	ModelMapper modelMapper;

	@PostMapping("register")
	public ResponseEntity<UserResponce>  register( @RequestBody UserRequest userRequest ) {
		
		UserDto user = modelMapper.map(userRequest, UserDto.class);

		UserDto newUser = authService.createUser(user);
		
		UserResponce userResponce = modelMapper.map(newUser, UserResponce.class);
		
		return new ResponseEntity<UserResponce> ( userResponce , HttpStatus.CREATED );
	}
	

}
