package com.alatamli.web.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.alatamli.web.entities.UserEntity;
import com.alatamli.web.enums.UserRole;
import com.alatamli.web.repositories.UserRepository;
import com.alatamli.web.shared.dto.UserDto;

@Service
public class UserService {
	
	@Autowired
	ModelMapper modelMapper;
	
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@Autowired
	UserRepository userRepository;

	public UserDto updateUser(long userId, UserDto user, String email ) {
		
		UserEntity userToUpdate = userRepository.findById(userId).orElseThrow( ( ) -> new IllegalArgumentException("User does not Exists !") );
		UserEntity userAuthed = userRepository.findByEmail(email);
		
		
		if( userAuthed.getRole() == UserRole.AGENT || (
			userAuthed.getRole() == UserRole.LEADER &&
			userToUpdate.getEntity().getId() != userAuthed.getEntity().getId() ) 
		) throw new SecurityException(" You Dont have permessions to change user infos");
		
		userToUpdate.setUsername(user.getUsername());
		userToUpdate.setSystemId(user.getSystemId());
		
		
		
		
		if( userAuthed.getRole() == UserRole.IT && user.getRole() != null )
			userToUpdate.setRole(user.getRole());
		
		UserEntity userUpdated = userRepository.save(userToUpdate);
		
		UserDto userDtoUpdated= modelMapper.map(userUpdated, UserDto.class);
		
		return userDtoUpdated;
	}

	public UserDto resetPassword(long userId, UserDto user, String email) {
		
		UserEntity userToUpdate = userRepository.findById(userId).orElseThrow( ( ) -> new IllegalArgumentException("User does not Exists !") );
		UserEntity userAuthed = userRepository.findByEmail(email);
		
		if( userAuthed.getRole() == UserRole.AGENT || (
				userAuthed.getRole() == UserRole.LEADER &&
				userToUpdate.getEntity().getId() != userAuthed.getEntity().getId() ) 
			) throw new SecurityException(" You Dont have permessions to change user");
		

		userToUpdate.setPassword( bCryptPasswordEncoder.encode(user.getPassword()) );
		
		UserEntity userUpdated = userRepository.save(userToUpdate);
		
		UserDto userDtoUpdated= modelMapper.map(userUpdated, UserDto.class);
		
		return userDtoUpdated;
		
	}

	public void deleteUser(long userId, String email ) {
		
		UserEntity userToDelete = userRepository.findById(userId).orElseThrow( ( ) -> new IllegalArgumentException("User does not Exists !") );
		UserEntity userAuthed = userRepository.findByEmail(email);
		
		if( userAuthed.getRole() == UserRole.AGENT || (
				userAuthed.getRole() == UserRole.LEADER &&
				userToDelete.getEntity().getId() != userAuthed.getEntity().getId() ) || 
				userToDelete.getRole() == UserRole.IT || userToDelete.getId() == userAuthed.getId()
				
			) throw new SecurityException(" You Dont have permessions to Delete this user");
		
		userRepository.delete(userToDelete);
		
	}

	public Object numberOfUsersOfEntity(String email) {
		
		UserEntity user = userRepository.findByEmail(email);
		Object numberOfUsersOfEntity = userRepository.numberOfUsersOfEntity( user.getEntity().getId() );//  user.getEntity().getUsers().size();		
		return numberOfUsersOfEntity;
	}

}
