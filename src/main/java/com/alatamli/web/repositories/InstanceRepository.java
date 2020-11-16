package com.alatamli.web.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alatamli.web.entities.InstanceEntity;

public interface InstanceRepository extends JpaRepository<InstanceEntity, Long> {

	InstanceEntity findByInstanceId(String string);

	@Query(value = "SELECT COUNT( i.id ) as data , i.created_at as dates FROM instances i GROUP BY DAY(i.created_at)" , nativeQuery = true)
	List<Object> getStatistiquesByDay();
	
	
	@Query(value = "SELECT COUNT(id) FROM `instances` WHERE Month( `created_at` ) = month(now()) ;" , nativeQuery = true)
	Object numberInstancesOfMonth();
	
	
	@Query(value = "SELECT pe.name , "
			+ " IFNULL( (select COUNT(i.id) from providers as p , accounts as a , instances as i WHERE p.id=a.provider_id and a.id=i.account_id and p.id=pe.id GROUP by p.name) , 0 ) as count_instnaces"
			+ " FROM entities_providers as ep , providers as pe WHERE ep.entity_id=:entityId and ep.provider_id=pe.id ORDER BY `count_instnaces` DESC;" , nativeQuery = true)
	List<Object> getNumberInstancesByProvider( @Param("entityId") long entityId );

	
	@Query(value = "SELECT eee.name , ifnull (" + 
			"  ( SELECT aaaaa.sum_pro from " + 
			"  ( select e.entity_id , SUM( e.prov_sum ) as sum_pro from" + 
			"  ( SELECT ep.entity_id ," + 
			"   ifnull ((" + 
 			"   select count(i.id) as proriver_count from instances as i , accounts as a , providers as p WHERE i.account_id = a.id and" + 
			"  a.provider_id=p.id and p.id=ep.provider_id and month(i.created_at)=:month GROUP by i.account_id " + 
			"  ) , 0 ) as prov_sum from entities_providers as ep ) as e GROUP by e.entity_id ) as aaaaa" + 
			"      WHERE aaaaa.entity_id=eee.id ) , 0 ) as dd" + 
			"    from entities as eee" , nativeQuery = true)
	List<Object> getNumberInstancesByProviderMonth( @Param("month") int month);

	
	@Query(value = "select e.name , ( select count(i.id) as count_instances from instances as i , accounts as a , entities_providers as ep"
			+ " WHERE ep.entity_id=e.id and a.provider_id=ep.provider_id and i.account_id=a.id and month(i.created_at)=:month ) as count_of_month"
			+ " from entities as e ;" , nativeQuery = true)
	List<Object> getStateOfEntitiesByMonth(@Param("month") int month);
	
}
