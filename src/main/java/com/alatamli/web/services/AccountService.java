package com.alatamli.web.services;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alatamli.web.entities.AccountEntity;
import com.alatamli.web.entities.AccountTwoKeysEntity;
import com.alatamli.web.entities.ProviderEntity;
import com.alatamli.web.entities.UserEntity;
import com.alatamli.web.repositories.AccountRepository;
import com.alatamli.web.repositories.ProviderRepository;
import com.alatamli.web.repositories.UserRepository;
import com.alatamli.web.shared.dto.AccountDto;
import com.alatamli.web.shared.dto.AccountTwoKeysDto;

@Service
public class AccountService {
	
	@Autowired 
	UserRepository userRepository;
	
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired 
	ProviderRepository providerRepository;
	
	@Autowired
	ModelMapper modelMapper;

	public List<AccountDto> getAccounts( long providerId , String email ){
		
		// UserEntity user = userRepository.findByEmail(email);
		
		
		// Type listType = new TypeToken<List<AccountDto>>() {}.getType();
		// List<AccountDto> accounts = modelMapper.map(user.getAccounts() , listType);
		
		List<AccountEntity> accountsEntity = accountRepository.findByProviderAndUser( providerId );
		
		Type listType = new TypeToken<List<AccountDto>>() {}.getType();
		List<AccountDto> accounts = modelMapper.map( accountsEntity , listType);
		
		return accounts;
		

	}

	public AccountDto addTwoKeysAccount(AccountTwoKeysDto accountTwoKeysDto , long providerId , String email) {
		
		UserEntity user = userRepository.findByEmail(email);
		ProviderEntity provider = providerRepository.findById(providerId).orElseThrow( () -> new IllegalArgumentException("there is no provider by this id") );
		
		AccountTwoKeysEntity accountEntity = modelMapper.map(accountTwoKeysDto, AccountTwoKeysEntity.class);
		
		accountEntity.setProvider(provider);
		Set<UserEntity> users = accountEntity.getUsers();
		users.add(user);
		accountEntity.setUsers( users );
		
		AccountTwoKeysEntity newAccount = accountRepository.save(accountEntity);
		
		AccountTwoKeysDto newAccountDto = modelMapper.map( newAccount , AccountTwoKeysDto.class );
		
		return newAccountDto;
	}

	public List<AccountDto> getAccountsTwoKeys(long providerId, String name) {
		
		List<AccountEntity> accountsEntity = accountRepository.findByProviderAndUser( providerId );
		
		Type listType = new TypeToken<List<AccountTwoKeysDto>>() {}.getType();
		List<AccountDto> accounts = modelMapper.map( accountsEntity , listType);
		
		return accounts;
	}
	
	
}
