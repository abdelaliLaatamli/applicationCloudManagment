package com.alatamli.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alatamli.web.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

	UserEntity findByEmail(String email);
	
}
