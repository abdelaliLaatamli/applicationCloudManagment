package com.alatamli.web.responses;

import java.util.HashSet;
import java.util.Set;


public class AccountResponse {
	

	protected long id;
	protected String name;
	protected String proxy;
	protected boolean isActive;
	protected Set<UserResponse> users = new HashSet<>();
	
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
	public String getProxy() {
		return proxy;
	}
	public void setProxy(String proxy) {
		this.proxy = proxy;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public Set<UserResponse> getUsers() {
		return users;
	}
	public void setUsers(Set<UserResponse> users) {
		this.users = users;
	}
	
	
}
