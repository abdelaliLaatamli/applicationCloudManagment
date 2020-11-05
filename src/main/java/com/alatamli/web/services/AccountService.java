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
import com.alatamli.web.enums.UserRole;
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

		List<AccountTwoKeysDto> accounts = new ArrayList<>();
		for (AccountEntity accountEntity : accountsEntity) {
			AccountTwoKeysDto account = modelMapper.map(accountEntity, AccountTwoKeysDto.class);
			accounts.add(account);
		}
		
		return accounts;
	}

	public List<AccountOneKeyDto> getAccountsOneKey(long providerId, String name) {
		
		List<AccountEntity> accountsEntity = accountRepository.findByProviderAndUser( providerId );

		List<AccountOneKeyDto> accounts = new ArrayList<>();
		for (AccountEntity accountEntity : accountsEntity) {
			AccountOneKeyDto account = modelMapper.map(accountEntity, AccountOneKeyDto.class);
			accounts.add(account);
		}
		
		return accounts;
	}

	public List<AccountFourKeysDto> getAccountsFourKeys(long providerId, String name) {
		List<AccountEntity> accountsEntity = accountRepository.findByProviderAndUser( providerId );
		
		
		List<AccountFourKeysDto> accounts = new ArrayList<>();
		for (AccountEntity accountEntity : accountsEntity) {
			AccountFourKeysDto account = modelMapper.map(accountEntity, AccountFourKeysDto.class);
			accounts.add(account);
		}
		
		
		return accounts;
	}

	public void deactivateAccount(long accountId , String email ) {
		
		UserEntity user = userRepository.findByEmail(email);
		
		AccountEntity accountEntity = accountRepository.findById(accountId)
											.orElseThrow(() -> new IllegalArgumentException("there is no Account by this id"));
		
		if( user.getRole() == UserRole.AGENT || 
			(user.getRole() == UserRole.LEADER && !accountEntity.getProvider().getEntities().contains(user.getEntity())) )
				throw new RuntimeException("You don't have the permission");
		accountEntity.setActive(false);
		
		accountRepository.save(accountEntity);
		
	}

	public AccountDto editOneKeyAccount(long accountId, AccountOneKeyDto accountOneKeyDto, String name) {
		
		AccountEntity accountEntity = accountRepository.findById(accountId).orElseThrow( () -> new IllegalArgumentException("there is no Account by this id") );
		
		AccountOneKeyEntity account = modelMapper.map(accountEntity, AccountOneKeyEntity.class);
		
		account.setName(accountOneKeyDto.getName());
		account.setToken(accountOneKeyDto.getToken());
		
		AccountOneKeyEntity newAccount = accountRepository.save(account);
		AccountOneKeyDto newAccountDto = modelMapper.map( newAccount , AccountOneKeyDto.class );
		
		return newAccountDto;

	}

	public AccountDto editTwoKeysAccount(long accountId, AccountTwoKeysDto accountTwoKeysDto, String name) {
		
		AccountEntity accountEntity = accountRepository.findById(accountId).orElseThrow( () -> new IllegalArgumentException("there is no Account by this id") );
		
		AccountTwoKeysEntity account = modelMapper.map( accountEntity , AccountTwoKeysEntity.class);
		
		account.setName(accountTwoKeysDto.getName());
		account.setAccessKey(accountTwoKeysDto.getAccessKey());
		account.setSecriteKey(accountTwoKeysDto.getSecriteKey());
	
		
		AccountTwoKeysEntity newAccount = (AccountTwoKeysEntity) accountRepository.save(account);
		
		AccountTwoKeysDto newAccountDto = modelMapper.map( newAccount , AccountTwoKeysDto.class );
		
		return newAccountDto;
	}

	public AccountDto editFourKeysAccount(long accountId, AccountFourKeysDto accountFourKeysDto, String name) {
		
		AccountEntity accountEntity = accountRepository.findById(accountId).orElseThrow( () -> new IllegalArgumentException("there is no Account by this id") );
		
		AccountFourKeysEntity account = modelMapper.map(accountEntity, AccountFourKeysEntity.class);
		
		account.setName(accountFourKeysDto.getName());
		account.setAccessKey(accountFourKeysDto.getAccessKey());
		account.setSecriteKey(accountFourKeysDto.getSecriteKey());
		account.setSubscription(accountFourKeysDto.getSubscription());
		account.setTenant(accountFourKeysDto.getTenant());
		
		AccountFourKeysEntity newAccount = (AccountFourKeysEntity) accountRepository.save(account);
		
		AccountFourKeysDto newAccountDto = modelMapper.map( newAccount , AccountFourKeysDto.class );
		
		return newAccountDto;
	}

	
	public List<Object> getNumberAccountsByProvider(){
		
		List<Object> numbers = accountRepository.getNumberAccountsByProvider();
		
		return numbers;
	}

	


	
	
}
