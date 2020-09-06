package com.alatamli.web.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("A1K")
public class AccountOneKeyEntity extends AccountEntity {


	@Column
	private String token ;
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	
}
