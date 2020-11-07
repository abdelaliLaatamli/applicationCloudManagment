package com.alatamli.web.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alatamli.web.entities.ProviderEntity;

public interface ProviderRepository extends JpaRepository<ProviderEntity, Long> {
	
	
	@Query(value = "Select provider_name as provider_of_month from ( SELECT ( SELECT COUNT(i.id) from instances as i "
			+ "WHERE i.account_id = a.id and MONTH( i.created_at) = MONTH( now() )) as cinstances , a.name , p.name as provider_name "
			+ "FROM `accounts` as a , providers as p WHERE p.id = a.provider_id and a.is_active=1 "
			+ "ORDER BY `cinstances` DESC limit 1 ) as form" , nativeQuery = true)
	Object getNumberProviderOfMonth();
	
	@Query( value = "SELECT COUNT(a.id) as number_of_providers FROM `accounts` as a , users as u , entities_providers as ep "
			+ "WHERE a.is_active = 1 and u.id =:userId and ep.entity_id = u.entity_id and a.provider_id=ep.provider_id" , nativeQuery = true )
	Object numberOfProvidersOfEntity(@Param("userId") long userId);

}
