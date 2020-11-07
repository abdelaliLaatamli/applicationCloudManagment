package com.alatamli.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alatamli.web.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

	UserEntity findByEmail(String email);

	@Query( value = "SELECT COUNT(u.id) as number_users FROM users as u WHERE u.entity_id=:entityId" , nativeQuery = true )
	Object numberOfUsersOfEntity( @Param("entityId") long entityId);

	
}
