package com.alatamli.web.shared.dto;

import com.alatamli.web.entities.EntityEntity;

public class UserDto {

	private long id ;
	private String email;
	private String username;
	private String password;
	private int systemId ;
	private EntityEntity entity;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getSystemId() {
		return systemId;
	}
	public void setSystemId(int systemId) {
		this.systemId = systemId;
	}
	public EntityEntity getEntity() {
		return entity;
	}
	public void setEntity(EntityEntity entity) {
		this.entity = entity;
	}
	
}
