package com.alatamli.web.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	
	
	@ManyToMany( fetch = FetchType.LAZY , cascade = CascadeType.ALL )
	@JoinTable(name="providers_entities" , 
					joinColumns = {@JoinColumn(name="provider_id")},
					inverseJoinColumns = { @JoinColumn(name="entity_id") }
			)
	private Set<EntityEntity> entities = new HashSet<>();
	

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

	public Set<EntityEntity> getEntities() {
		return entities;
	}

	public void setEntities(Set<EntityEntity> entities) {
		this.entities = entities;
	}

}
