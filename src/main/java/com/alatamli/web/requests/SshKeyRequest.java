package com.alatamli.web.requests;


public class SshKeyRequest {

	private String privateKey;
	private String publicKey;
	private AccountRequest account ;
	
	
	
	public AccountRequest getAccount() {
		return account;
	}
	public void setAccount(AccountRequest account) {
		this.account = account;
	}
	public String getPrivateKey() {
		return privateKey;
	}
	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}
	public String getPublicKey() {
		return publicKey;
	}
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}


}
