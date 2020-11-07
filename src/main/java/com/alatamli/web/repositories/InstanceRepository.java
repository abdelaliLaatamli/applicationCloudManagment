package com.alatamli.web.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.alatamli.web.entities.InstanceEntity;

public interface InstanceRepository extends JpaRepository<InstanceEntity, Long> {

	InstanceEntity findByInstanceId(String string);

	@Query(value = "SELECT COUNT( i.id ) as data , i.created_at as dates FROM instances i GROUP BY DAY(i.created_at)" , nativeQuery = true)
	List<Object> getStatistiquesByDay();
	
	
	@Query(value = "SELECT COUNT(id) FROM `instances` WHERE Month( `created_at` ) = month(now()) ;" , nativeQuery = true)
	Object numberInstancesOfMonth();
	

}
