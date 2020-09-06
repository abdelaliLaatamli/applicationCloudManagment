package com.alatamli.web.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;


@Entity( name="ssh_keys" )
public class SshKeyEntity {
	
	@Id
	@GeneratedValue
	private long id ;
	
	@Lob
	@Column
	private String privateKey;
	
	@Lob
	@Column(nullable = true)
	private String publicKey;
	
	@OneToOne
	@JoinColumn(name="account_id")
	private AccountEntity account ;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public AccountEntity getAccount() {
		return account;
	}

	public void setAccount(AccountEntity account) {
		this.account = account;
	}
	
}
