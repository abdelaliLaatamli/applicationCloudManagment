package com.alatamli.web.requests;

import java.util.List;

import com.alatamli.web.enums.KeysType;


public class AccountRequest {
	
	
	private String name;
	private String proxy;
	private SshKeyRequest sshKey;
	
	private String token ;

	private String accessKey;
	private String secriteKey;
	private List<RegionRequest> regions ;
	

	private String subscription;
	private String tenant;
	
	
	private KeysType accountType;

	
	
	public KeysType getAccountType() {
		return accountType;
	}
	public void setAccountType(KeysType accountType) {
		this.accountType = accountType;
	}
	public SshKeyRequest getSshKey() {
		return sshKey;
	}
	public void setSshKey(SshKeyRequest sshKey) {
		this.sshKey = sshKey;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getAccessKey() {
		return accessKey;
	}
	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}
	public String getSecriteKey() {
		return secriteKey;
	}
	public void setSecriteKey(String secriteKey) {
		this.secriteKey = secriteKey;
	}
	public List<RegionRequest> getRegions() {
		return regions;
	}
	public void setRegions(List<RegionRequest> regions) {
		this.regions = regions;
	}
	public String getSubscription() {
		return subscription;
	}
	public void setSubscription(String subscription) {
		this.subscription = subscription;
	}
	public String getTenant() {
		return tenant;
	}
	public void setTenant(String tenant) {
		this.tenant = tenant;
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

	
	
	
}
