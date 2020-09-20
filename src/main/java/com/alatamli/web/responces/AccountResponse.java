package com.alatamli.web.responces;


public class AccountResponse {
	

	protected long id;
	protected String name;
	protected String proxy;
	protected boolean isActive;
	
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
	
	
	/*
	private ProviderResponce provider;
	private SshKeyResponse sshKey;
	private List<UserEntity> users;
	private List<InstanceEntity> instances ;
*/
}
