package com.alatamli.web.requests;

import java.time.Instant;

import com.alatamli.web.enums.IpStatus;


public class InstanceRequest {
	
	
	private long id ;
	private String instanceId;
	private String name;
	private String vmtaDomain;
	private String mainIp;
	private boolean isInstalled;
	private boolean isDeleted;
	private Instant createdAt;
	private Instant deletedAt;
	private IpStatus ipStatus;
	//private AccountRequest account;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getInstanceId() {
		return instanceId;
	}
	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVmtaDomain() {
		return vmtaDomain;
	}
	public void setVmtaDomain(String vmtaDomain) {
		this.vmtaDomain = vmtaDomain;
	}
	public String getMainIp() {
		return mainIp;
	}
	public void setMainIp(String mainIp) {
		this.mainIp = mainIp;
	}
	public boolean isInstalled() {
		return isInstalled;
	}
	public void setInstalled(boolean isInstalled) {
		this.isInstalled = isInstalled;
	}
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	public Instant getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}
	public Instant getDeletedAt() {
		return deletedAt;
	}
	public void setDeletedAt(Instant deletedAt) {
		this.deletedAt = deletedAt;
	}
	public IpStatus getIpStatus() {
		return ipStatus;
	}
	public void setIpStatus(IpStatus ipStatus) {
		this.ipStatus = ipStatus;
	}
	/*
	public AccountRequest getAccount() {
		return account;
	}
	public void setAccount(AccountRequest account) {
		this.account = account;
	}*/

}
