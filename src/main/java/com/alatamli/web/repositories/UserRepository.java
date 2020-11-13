package com.alatamli.web.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alatamli.web.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

	UserEntity findByEmail(String email);

	@Query( value = "SELECT COUNT(u.id) as number_users FROM users as u WHERE u.entity_id=:entityId" , nativeQuery = true )
	Object numberOfUsersOfEntity( @Param("entityId") long entityId);

	
	@Query( value = "SELECT * FROM users as u WHERE u.entity_id=:entityId" , nativeQuery = true )
	List<UserEntity> getAllByEntityId( @Param("entityId") long id);
	
	
	@Query( value = "SELECT u.username , ( SELECT COUNT(id) as instances from instances as i WHERE i.user_id=u.id) as instances ,"
			+ " ( SELECT COUNT(i.id) as instances from instances as i WHERE i.user_id=u.id and month(i.created_at)=month(now())-1 ) as last_month "
			+ "	from users as u WHERE u.entity_id=:entityId ORDER BY `instances` DESC LIMIT 6" , nativeQuery = true )
	List<Object> getUsersStatiques( @Param("entityId") long id);

	
}
