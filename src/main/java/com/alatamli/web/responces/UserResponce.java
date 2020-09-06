package com.alatamli.web.responces;




public class UserResponce {
	
	private long id ;
	private String email;
	private String username;
	private int systemId ;	
	private EntityResponce entity;
	
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
	public EntityResponce getEntity() {
		return entity;
	}
	public void setEntity(EntityResponce entity) {
		this.entity = entity;
	}

}
