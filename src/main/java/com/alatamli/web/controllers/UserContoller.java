package com.alatamli.web.controllers;

import java.lang.reflect.Type;
import java.security.Principal;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alatamli.web.requests.UserRequest;
import com.alatamli.web.responses.UserResponse;
import com.alatamli.web.services.AuthService;
import com.alatamli.web.services.UserService;
import com.alatamli.web.shared.dto.UserDto;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
public class UserContoller {
	
	
	@Autowired
	AuthService authService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ModelMapper modelMapper;
	
	
	@GetMapping
	public ResponseEntity<List<UserResponse>> getUsers( Principal principal ){
		
		List<UserDto> users = userService.getUsers( principal.getName() );
		
		Type listType  = new TypeToken<List<UserResponse>>() {}.getType(); 
		List<UserResponse> listUsers = modelMapper.map( users , listType);
		
		return new ResponseEntity<List<UserResponse>>( listUsers , HttpStatus.OK);
	}
	
	
	@PostMapping("register")
	public ResponseEntity<UserResponse>  register( @RequestBody UserRequest userRequest ) {
		
		UserDto user = modelMapper.map(userRequest, UserDto.class);

		UserDto newUser = authService.createUser(user);
		
		UserResponse userResponce = modelMapper.map(newUser, UserResponse.class);
		
		return new ResponseEntity<UserResponse> ( userResponce , HttpStatus.CREATED );
	}
	
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserResponse> updateUser( @PathVariable("userId") long userId , @RequestBody UserRequest userRequest , Principal principal  ) {
		
		UserDto user = modelMapper.map(userRequest, UserDto.class);
		
		UserDto userUpdated = userService.updateUser( userId , user , principal.getName() );
		
		UserResponse userResponce = modelMapper.map(userUpdated, UserResponse.class);
		
		return new ResponseEntity<UserResponse> ( userResponce , HttpStatus.ACCEPTED );
	}
	
	@PutMapping("/{userId}/pwdreset")
	public ResponseEntity<UserResponse> resetpassword( @PathVariable("userId") long userId , @RequestBody UserRequest userRequest , Principal principal ) {
		
		
		UserDto user = modelMapper.map(userRequest, UserDto.class);
		
		UserDto userUpdated = userService.resetPassword( userId , user , principal.getName() );
		
		UserResponse userResponce = modelMapper.map(userUpdated, UserResponse.class);
		
		return new ResponseEntity<UserResponse> ( userResponce , HttpStatus.ACCEPTED );
		
	}
	
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<Object> deleteUser( @PathVariable("userId") long userId , Principal principal ) {
		
		userService.deleteUser( userId , principal.getName() );
		
		return new ResponseEntity<> ( HttpStatus.NO_CONTENT );
		
	}
	
	
	

}
