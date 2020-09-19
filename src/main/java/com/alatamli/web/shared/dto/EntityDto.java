package com.alatamli.web.shared.dto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EntityDto {
	
	private long id;
	private String name;
	private int systemId;
	private List<UserDto> users ;
	private Set<ProviderDto> providers = new HashSet<>();
	

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
	
	public List<UserDto> getUsers() {
		return users;
	}
	public void setUsers(List<UserDto> users) {
		this.users = users;
	}
	public Set<ProviderDto> getProviders() {
		return providers;
	}
	public void setProviders(Set<ProviderDto> providers) {
		this.providers = providers;
	}
	
	

}
