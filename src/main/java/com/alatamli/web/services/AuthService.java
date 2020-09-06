package com.alatamli.web.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.alatamli.web.entities.EntityEntity;
import com.alatamli.web.entities.UserEntity;
import com.alatamli.web.repositories.EntityRepository;
import com.alatamli.web.repositories.UserRepository;
import com.alatamli.web.shared.dto.UserDto;

@Service
public class AuthService {

	@Autowired
	ModelMapper modelMapper;
	
	
	@Autowired
	EntityRepository entityRepository;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@Autowired
	UserRepository userRepository;
	
	
	public UserDto createUser( UserDto userDto ) {
		
		UserEntity userExist = userRepository.findByEmail(userDto.getEmail());
		
		if( userExist != null ) throw new RuntimeException("User Alredy Exists !") ;
		
		UserEntity user = modelMapper.map(userDto, UserEntity.class);
		
		if( user.getId() != 0 ) user.setId(0);
		
		EntityEntity entity = entityRepository.findById( 1L ).orElseThrow(() -> new IllegalArgumentException("there is no entity have this id") );
		
		user.setPassword( bCryptPasswordEncoder.encode(user.getPassword() ) );
		
		user.setEntity(entity);
		
		UserEntity newUserEntity = userRepository.save(user);
		
		UserDto newUser = modelMapper.map(newUserEntity, UserDto.class);
		
		return newUser;
		
	}
	
}
