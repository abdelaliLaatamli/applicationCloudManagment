package com.alatamli.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.alatamli.web.entities.CronEntity;

public interface CronRepository extends JpaRepository<CronEntity, Long> {

	
	@Query( value = "SELECT COUNT(id) as number_of_tasks FROM crons WHERE month(created_at)=month(now())" , nativeQuery = true )
	Object numberOfTasks();

}
