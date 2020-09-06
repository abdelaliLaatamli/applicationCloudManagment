package com.alatamli.web.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="regions")
public class RegionEntity {
	
	@Id
	@GeneratedValue
	private long id ;
	
	@Column
	private String regionCode;
	
	@Column
	private String privateKey;
	
	@Column
	private String publicKey;
	
	@Column
	private String thumpPoint;
	
	
	@ManyToOne
	@JoinColumn(name="account_id")
	private AccountTwoKeysEntity account;


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getRegionCode() {
		return regionCode;
	}


	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
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


	public String getThumpPoint() {
		return thumpPoint;
	}


	public void setThumpPoint(String thumpPoint) {
		this.thumpPoint = thumpPoint;
	}


	public AccountTwoKeysEntity getAccount() {
		return account;
	}


	public void setAccount(AccountTwoKeysEntity account) {
		this.account = account;
	}
	
}
