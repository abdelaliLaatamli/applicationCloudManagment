package com.alatamli.web.requests;

import java.util.List;

import com.alatamli.web.enums.KeysType;
import com.alatamli.web.shared.dto.EntityDto;

public class ProviderRequest {
	

	private String name;
	
	private KeysType providerKeysType ;
	
	private List<EntityDto> entities ;
	
	public List<EntityDto> getEntities() {
		return entities;
	}
	public void setEntities(List<EntityDto> entities) {
		this.entities = entities;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public KeysType getProviderKeysType() {
		return providerKeysType;
	}
	public void setProviderKeysType(KeysType providerKeysType) {
		this.providerKeysType = providerKeysType;
	}

}
