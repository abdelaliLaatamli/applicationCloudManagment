package com.alatamli.web.responces;




public class UserResponse {
	
	private long id ;
	private String email;
	private String username;
	private int systemId ;	
	private EntityResponse entity;
	
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
	public int getSystemId() {
		return systemId;
	}
	public void setSystemId(int systemId) {
		this.systemId = systemId;
	}
	public EntityResponse getEntity() {
		return entity;
	}
	public void setEntity(EntityResponse entity) {
		this.entity = entity;
	}

}
