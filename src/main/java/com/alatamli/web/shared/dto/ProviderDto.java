package com.alatamli.web.shared.dto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.alatamli.web.enums.KeysType;


public class ProviderDto {

	private long id;
	private String name;
	private KeysType providerKeysType;
	private List<AccountDto> accounts ;
	private Set<EntityDto> entities = new HashSet<>() ;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<AccountDto> getAccounts() {
		return accounts;
	}
	public void setAccounts(List<AccountDto> accounts) {
		this.accounts = accounts;
	}
	public Set<EntityDto> getEntities() {
		return entities;
	}
	public void setEntities(Set<EntityDto> entities) {
		this.entities = entities;
	}
	public KeysType getProviderKeysType() {
		return providerKeysType;
	}
	public void setProviderKeysType(KeysType providerKeysType) {
		this.providerKeysType = providerKeysType;
	}
	
	

}
