package com.alatamli.web.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity(name="accounts")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn( name="TYPE_ACC" , discriminatorType = DiscriminatorType.STRING , length = 3 )

public abstract class AccountEntity {

	@Id
	@GeneratedValue
	private long id;
	
	@Column( nullable = false , length = 80 )
	private String name;
	
	@Column( nullable = true )
	private String proxy;
	
	@Column(nullable = false)
	private boolean isActive = true;
	
	@ManyToOne
	@JoinColumn(name="provider_id")
	private ProviderEntity provider;
	
	@OneToOne(mappedBy = "account" , cascade = CascadeType.ALL)
	private SshKeyEntity sshKey;
	
	@ManyToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
	@JoinTable( name="accounts_users" ,
				joinColumns = { @JoinColumn(name="account_id") } ,
				inverseJoinColumns = {@JoinColumn(name="user_id")}
			)
	private Set<UserEntity> users = new HashSet<>();
	
	@OneToMany(cascade = CascadeType.ALL , mappedBy = "account")
	private List<InstanceEntity> instances ;

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

	public ProviderEntity getProvider() {
		return provider;
	}

	public void setProvider(ProviderEntity provider) {
		this.provider = provider;
	}

	public SshKeyEntity getSshKey() {
		return sshKey;
	}

	public void setSshKey(SshKeyEntity sshKey) {
		this.sshKey = sshKey;
	}

	public Set<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(Set<UserEntity> users) {
		this.users = users;
	}
	
	
}
