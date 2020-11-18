package com.alatamli.web.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.alatamli.web.entities.CronEntity;

public interface CronRepository extends JpaRepository<CronEntity, Long> {

	
	@Query( value = "SELECT COUNT(id) as number_of_tasks FROM crons WHERE month(created_at)=month(now())" , nativeQuery = true )
	Object numberOfTasks();
	
	@Query( value = "SELECT c.* FROM crons as c , instances as i "
						+ "	WHERE c.is_started=0 and c.is_stoped=0 "
						+ " and i.is_deleted=0 and i.id=c.instance_id and "
						+ " ( "
						+ "		( SELECT TIMESTAMPDIFF(MINUTE, c.last_execute , now() ) ) > c.delay_between "
						+ "			or ISNULL(c.last_execute)"
						+ " )" , nativeQuery = true )
	List<CronEntity> tasksToRun();

}
