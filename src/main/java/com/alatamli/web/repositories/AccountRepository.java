package com.alatamli.web.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alatamli.web.entities.AccountEntity;
import com.alatamli.web.entities.AccountOneKeyEntity;
import com.alatamli.web.shared.dto.AccountOneKeyDto;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
	
	
	// @Query(value = "SELECT * FROM users u WHERE ( u.first_name LIKE %:search% OR u.last_name Like %:search% ) AND u.email_verification_status= :status " , nativeQuery = true)
	@Query(value = "SELECT * FROM accounts u WHERE"+
				" u.provider_id = :providerId And"+
				" u.is_active = 1" ,
			nativeQuery = true)
	List<AccountEntity> findByProviderAndUser( @Param("providerId") long providerId);

	AccountOneKeyEntity save(AccountOneKeyDto account);

	
	// @Query(value = "SELECT COUNT( i.id ) as data , i.created_at as dates FROM instances i GROUP BY DAY(i.created_at)" , nativeQuery = true)
	@Query(value = "SELECT count(a.id) as number , p.name FROM accounts as a ,"
					+ " providers as p WHERE p.id = provider_id "
					+ "GROUP BY a.provider_id" , nativeQuery = true)
	List<Object> getNumberAccountsByProvider();

}
