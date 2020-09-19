package com.alatamli.web.requests;

import com.alatamli.web.enums.KeysType;

public class ProviderRequest {
	

	private String name;
	
	private KeysType providerKeysType ;
	
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
