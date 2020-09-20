package com.alatamli.web.shared.dto;


public class AccountDto {

	protected long id;
	protected String name;
	protected String proxy;
	protected boolean isActive = true;
	protected ProviderDto provider;
	protected SshKeyDto sshKey ;
	
	
	public SshKeyDto getSshKey() {
		return sshKey;
	}
	public void setSshKey(SshKeyDto sshKey) {
		this.sshKey = sshKey;
	}
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
	public ProviderDto getProvider() {
		return provider;
	}
	public void setProvider(ProviderDto provider) {
		this.provider = provider;
	}
	
}
