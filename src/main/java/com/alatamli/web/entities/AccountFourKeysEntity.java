package com.alatamli.web.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("A4K")
public class AccountFourKeysEntity extends AccountEntity {
	
	@Column 
	private String subscription;
	
	@Column
	private String accessKey;
	
	
	@Column
	private String secriteKey;
	
	@Column
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
