package com.alatamli.web.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.alatamli.web.enums.UserRole;

@Entity(name="users")
public class UserEntity {
	
	@Id
	@GeneratedValue
	private long id ;
	
	@Column( unique = true , nullable = false , length = 80 )
	private String email;
	
	@Column( nullable = false , length = 50)
	private String username;
	
	@Column( nullable = false )
	private String password;
	
	@Column( nullable = true )
	private int systemId ;
	
	@Enumerated(EnumType.ORDINAL)
	private UserRole role = UserRole.AGENT;
	


	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="entity_id")
	private EntityEntity entity;
	
	
	@ManyToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL , mappedBy = "users")
	private Set<AccountEntity> accounts = new HashSet<>();

	
	public UserRole getRole() {
		return role;
	}


	public void setRole(UserRole role) {
		this.role = role;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public int getSystemId() {
		return systemId;
	}


	public void setSystemId(int systemId) {
		this.systemId = systemId;
	}


	public EntityEntity getEntity() {
		return entity;
	}


	public void setEntity(EntityEntity entity) {
		this.entity = entity;
	}


	public Set<AccountEntity> getAccounts() {
		return accounts;
	}


	public void setAccounts(Set<AccountEntity> accounts) {
		this.accounts = accounts;
	}
	

	

}
