package com.alatamli.web.responses;

public class AccountResponseFourKeys extends AccountResponse {
	
	private String subscription;
	private String accessKey;
	private String secriteKey;
	private String tenant;
	
	
	public String getSubscription() {
		return subscription;
	}
	public void setSubscription(String subscription) {
		this.subscription = subscription;
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
	public String getTenant() {
		return tenant;
	}
	public void setTenant(String tenant) {
		this.tenant = tenant;
	}
	
	

}
