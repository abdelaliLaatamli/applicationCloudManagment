package com.alatamli.web.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "providers")
public class ProviderEntity {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column
	private String name;
	
	@OneToMany( cascade = CascadeType.ALL , mappedBy = "provider" )
	private List<AccountEntity> accounts ;

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

	public List<AccountEntity> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<AccountEntity> accounts) {
		this.accounts = accounts;
	}

}
