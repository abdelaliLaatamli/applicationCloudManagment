package com.alatamli.web.responses;


public class AccountResponseTwoKeys extends AccountResponse {
	
	private String accessKey;
	private String secriteKey;
	
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
	
	

}
