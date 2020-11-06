package com.alatamli.web.responses;

import java.time.Instant;
import com.alatamli.web.enums.IpStatus;

public class InstanceResponse {


	private long id ;
	private String instanceId;
	private String name;
	private String vmtaDomain;
	private String mainIp;
	private boolean isInstalled=false;
	private boolean isDeleted=false;
	private Instant createdAt = Instant.now();
	private Instant deletedAt;
	private IpStatus ipStatus;
	private AccountResponse account;
	
	
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
	public AccountResponse getAccount() {
		return account;
	}
	public void setAccount(AccountResponse account) {
		this.account = account;
	}
	
	
	
}
