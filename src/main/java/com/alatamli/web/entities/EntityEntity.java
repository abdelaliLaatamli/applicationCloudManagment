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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;



@Entity(name = "entities")
public class EntityEntity {

	@Id
	@GeneratedValue
	private long id;
	
	@Column( nullable = false , length = 60 )
	private String name;
	
	@Column( nullable = false )
	private int systemId;
	
	
	@OneToMany( cascade = CascadeType.ALL , mappedBy = "entity" )
	private List<UserEntity> users ;
	
	@ManyToMany( fetch = FetchType.LAZY , cascade = CascadeType.ALL , mappedBy = "entities" )
	private Set<ProviderEntity> providers = new HashSet<>();


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


	public int getSystemId() {
		return systemId;
	}


	public void setSystemId(int systemId) {
		this.systemId = systemId;
	}


	public List<UserEntity> getUsers() {
		return users;
	}


	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}


	public Set<ProviderEntity> getProviders() {
		return providers;
	}


	public void setProviders(Set<ProviderEntity> providers) {
		this.providers = providers;
	}
	
}
