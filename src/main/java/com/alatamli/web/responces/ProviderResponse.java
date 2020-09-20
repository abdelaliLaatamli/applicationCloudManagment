package com.alatamli.web.responces;

import com.alatamli.web.enums.KeysType;

public class ProviderResponse {
	
	protected long id;
	protected String name;
	protected KeysType providerKeysType;
	
	
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
	public KeysType getProviderKeysType() {
		return providerKeysType;
	}
	public void setProviderKeysType(KeysType providerKeysType) {
		this.providerKeysType = providerKeysType;
	}
	
	

}
