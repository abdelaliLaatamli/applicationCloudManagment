package com.alatamli.web.responces;

import java.util.List;

public class ProviderResponseDetails extends ProviderResponse {

	private List<AccountResponse> accounts ;
	private List<EntityResponse> entities ;
	
	
	public List<AccountResponse> getAccounts() {
		return accounts;
	}
	public void setAccounts(List<AccountResponse> accounts) {
		this.accounts = accounts;
	}
	public List<EntityResponse> getEntities() {
		return entities;
	}
	public void setEntities(List<EntityResponse> entities) {
		this.entities = entities;
	}
	

	
	
}
