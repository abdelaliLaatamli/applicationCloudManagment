package com.alatamli.web.services;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alatamli.web.entities.AccountEntity;
import com.alatamli.web.entities.AccountFourKeysEntity;
import com.alatamli.web.entities.AccountOneKeyEntity;
import com.alatamli.web.entities.AccountTwoKeysEntity;
import com.alatamli.web.entities.ProviderEntity;
import com.alatamli.web.entities.RegionEntity;
import com.alatamli.web.entities.UserEntity;
import com.alatamli.web.repositories.AccountRepository;
import com.alatamli.web.repositories.ProviderRepository;
import com.alatamli.web.repositories.UserRepository;
import com.alatamli.web.shared.dto.AccountDto;
import com.alatamli.web.shared.dto.AccountFourKeysDto;
import com.alatamli.web.shared.dto.AccountOneKeyDto;
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
		
		
		List<AccountEntity> accountsEntity = accountRepository.findByProviderAndUser( providerId );
		
		Type listType = new TypeToken<List<AccountDto>>() {}.getType();
		List<AccountDto> accounts = modelMapper.map( accountsEntity , listType);
		
		return accounts;
		

	}

	public AccountDto addTwoKeysAccount(AccountTwoKeysDto accountTwoKeysDto , long providerId , String email) {
		
		UserEntity user = userRepository.findByEmail(email);
		
		ProviderEntity provider = providerRepository.findById(providerId)
									.orElseThrow( () -> new IllegalArgumentException("there is no provider by this id") );
		
		AccountTwoKeysEntity accountEntity = modelMapper.map(accountTwoKeysDto, AccountTwoKeysEntity.class);
		
		accountEntity.setProvider(provider);
		
		if( accountEntity.getRegions() != null ) {
			
			RegionEntity region = accountEntity.getRegions().get(0);
			region.setAccount(accountEntity);
			ArrayList<RegionEntity> na = new ArrayList<>();
			na.add( region );
			accountEntity.setRegions( na );
		}	

		
		
		
		Set<UserEntity> users = accountEntity.getUsers();
		users.add(user);
		accountEntity.setUsers( users );
		
		if( accountEntity.getSshKey() != null )
		accountEntity.getSshKey().setAccount(accountEntity);
		
		AccountTwoKeysEntity newAccount = accountRepository.save(accountEntity);
		
		AccountTwoKeysDto newAccountDto = modelMapper.map( newAccount , AccountTwoKeysDto.class );
		
		return newAccountDto;
	}
	
	public AccountDto addOneKeyAccount(AccountOneKeyDto accountOneKeyDto, long providerId, String email) {
		
		UserEntity user = userRepository.findByEmail(email);
		
		ProviderEntity provider = providerRepository.findById(providerId)
									.orElseThrow( () -> new IllegalArgumentException("there is no provider by this id") );
		
		AccountOneKeyEntity accountEntity = modelMapper.map(accountOneKeyDto, AccountOneKeyEntity.class);
		
		accountEntity.setProvider(provider);
		
		Set<UserEntity> users = accountEntity.getUsers();
		users.add(user);
		accountEntity.setUsers( users );
		
		if( accountEntity.getSshKey() != null )
		accountEntity.getSshKey().setAccount(accountEntity);
		
		AccountOneKeyEntity newAccount = accountRepository.save(accountEntity);
		
		AccountOneKeyDto newAccountDto = modelMapper.map( newAccount , AccountOneKeyDto.class );
		
		return newAccountDto;
	}
	
	public AccountDto addFourKeysAccount(AccountFourKeysDto accountFourKeysDto, long providerId, String email) {
		
		UserEntity user = userRepository.findByEmail(email);
		
		ProviderEntity provider = providerRepository.findById(providerId)
									.orElseThrow( () -> new IllegalArgumentException("there is no provider by this id") );
		
		AccountFourKeysEntity accountEntity = modelMapper.map(accountFourKeysDto, AccountFourKeysEntity.class);
		
		accountEntity.setProvider(provider);
		
		Set<UserEntity> users = accountEntity.getUsers();
		users.add(user);
		accountEntity.setUsers( users );
		
		if( accountEntity.getSshKey() != null )
		accountEntity.getSshKey().setAccount(accountEntity);
		
		AccountFourKeysEntity newAccount = accountRepository.save(accountEntity);
		
		AccountFourKeysDto newAccountDto = modelMapper.map( newAccount , AccountFourKeysDto.class );
		
		return newAccountDto;
	}

	public List<AccountTwoKeysDto> getAccountsTwoKeys(long providerId, String name) {
		
		List<AccountEntity> accountsEntity = accountRepository.findByProviderAndUser( providerId );
		/*
		Type listType = new TypeToken<List<AccountTwoKeysDto>>() {}.getType();
		List<AccountTwoKeysDto> accounts = modelMapper.map( accountsEntity , listType);
		*/
		
		List<AccountTwoKeysDto> accounts = new ArrayList<>();
		for (AccountEntity accountEntity : accountsEntity) {
			AccountTwoKeysDto account = modelMapper.map(accountEntity, AccountTwoKeysDto.class);
			accounts.add(account);
		}
		
		return accounts;
	}

	public List<AccountOneKeyDto> getAccountsOneKey(long providerId, String name) {
		
		List<AccountEntity> accountsEntity = accountRepository.findByProviderAndUser( providerId );
		/*
		Type listType = new TypeToken<List<AccountOneKeyDto>>() {}.getType();
		List<AccountOneKeyDto> accounts = modelMapper.map( accountsEntity , listType);
		*/
		List<AccountOneKeyDto> accounts = new ArrayList<>();
		for (AccountEntity accountEntity : accountsEntity) {
			AccountOneKeyDto account = modelMapper.map(accountEntity, AccountOneKeyDto.class);
			accounts.add(account);
		}
		
		return accounts;
	}

	public List<AccountFourKeysDto> getAccountsFourKeys(long providerId, String name) {
		List<AccountEntity> accountsEntity = accountRepository.findByProviderAndUser( providerId );
		
		/*
		Type listType = new TypeToken<List<AccountFourKeysDto>>() {}.getType();
		List<AccountFourKeysDto> accounts = modelMapper.map( accountsEntity , listType);
		*/
		
		List<AccountFourKeysDto> accounts = new ArrayList<>();
		for (AccountEntity accountEntity : accountsEntity) {
			AccountFourKeysDto account = modelMapper.map(accountEntity, AccountFourKeysDto.class);
			accounts.add(account);
		}
		
		
		return accounts;
	}

	


	
	
}
