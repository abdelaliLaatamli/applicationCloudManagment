package com.alatamli.web.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alatamli.web.entities.AccountEntity;
import com.alatamli.web.entities.AccountOneKeyEntity;
import com.alatamli.web.shared.dto.AccountOneKeyDto;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
	
	AccountOneKeyEntity save(AccountOneKeyDto account);
	
	@Query(value = "SELECT * FROM accounts u WHERE"+
				" u.provider_id = :providerId And"+
				" u.is_active = 1" ,
			nativeQuery = true)
	List<AccountEntity> findByProviderAndUser( @Param("providerId") long providerId);
	

	@Query(value = "SELECT count(a.id) as number , p.name FROM accounts as a ,"
					+ " providers as p WHERE p.id = provider_id "
					+ "GROUP BY a.provider_id ORDER BY `number` DESC" , nativeQuery = true)
	List<Object> getNumberAccountsByProvider();
	
	
	
	@Query(value = "SELECT ( SELECT COUNT(i.id) from instances as i WHERE i.account_id = a.id) as cinstances , "
					+ " a.name , p.name as provider_name "
					+ " , ( SELECT MAX(i.created_at) from instances as i WHERE i.account_id = a.id) as lastDate "
					+ " FROM `accounts` as a , providers as p WHERE p.id = a.provider_id"
					+ " ORDER BY `cinstances` DESC limit 5" , nativeQuery = true)
	List<Object> getNumberInstanceByAccount();

}
