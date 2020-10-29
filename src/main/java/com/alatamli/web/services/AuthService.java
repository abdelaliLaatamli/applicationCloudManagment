package com.alatamli.web.services;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.alatamli.web.entities.EntityEntity;
import com.alatamli.web.entities.UserEntity;
import com.alatamli.web.enums.UserRole;
import com.alatamli.web.repositories.EntityRepository;
import com.alatamli.web.repositories.UserRepository;
import com.alatamli.web.shared.dto.UserDto;

@Service
public class AuthService implements UserDetailsService {

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
		
		if( user.getRole() == null ) user.setRole(UserRole.AGENT);
		
		UserEntity newUserEntity = userRepository.save(user);
		
		UserDto newUser = modelMapper.map(newUserEntity, UserDto.class);
		
		return newUser;
		
	}

	public UserDto getUser(String email ) {
		
		UserEntity user = userRepository.findByEmail(email);
		
		//if( user == null ) throw new RuntimeException("user not found !! " + email );
		if( user == null ) throw new UsernameNotFoundException("User not Exist : " + email);
		
		
		UserDto userDto = modelMapper.map(user, UserDto.class);
		
		return userDto ;
		
	}
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserEntity userEntity =  userRepository.findByEmail(username);
		
		if( userEntity == null ) throw new UsernameNotFoundException("User not Exist : " + username);
		
		
		//if( userEntity == null )  throw new UsernameNotFoundException(SpringSecurityMessageSource.getAccessor().getMessage("AbstractUserDetailsAuthenticationProvider.UserUnknown",new Object[] {username},"User is not known"));

		
		return new User(userEntity.getEmail() , userEntity.getPassword() , new ArrayList<>()) ;
	}
	
}
