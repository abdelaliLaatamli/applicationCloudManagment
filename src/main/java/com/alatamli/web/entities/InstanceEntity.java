package com.alatamli.web.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.alatamli.web.enums.IpStatus;

@Entity(name = "instances")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_ISTANCE" , discriminatorType = DiscriminatorType.STRING , length = 3)
public class InstanceEntity {
	


	@Id
	@GeneratedValue
	private long id ;
	
	@Column
	private String instanceId;
	
	@Column(nullable = false , length = 12)
	private String name;
	
	@Column(nullable = true )
	private String vmtaDomain;
	
	@Column( nullable = true)
	private String mainIp;
	
	@Column
	private boolean isInstalled=false;
	
	@Enumerated(EnumType.ORDINAL)
	private IpStatus ipStatus;
	
	@ManyToOne
	@JoinColumn(name="account_id")
	private AccountEntity account;
	
	
	@OneToMany(cascade = CascadeType.ALL , mappedBy = "instance")
	private List<VcnEntity> vcns;
	
	
	@OneToMany( cascade = CascadeType.ALL , mappedBy = "instance" )
	private List<CronEntity> crons ;
	
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


	public IpStatus getIpStatus() {
		return ipStatus;
	}


	public void setIpStatus(IpStatus ipStatus) {
		this.ipStatus = ipStatus;
	}


	public AccountEntity getAccount() {
		return account;
	}


	public void setAccount(AccountEntity account) {
		this.account = account;
	}


	public List<VcnEntity> getVcns() {
		return vcns;
	}


	public void setVcns(List<VcnEntity> vcns) {
		this.vcns = vcns;
	}


	public List<CronEntity> getCrons() {
		return crons;
	}


	public void setCrons(List<CronEntity> crons) {
		this.crons = crons;
	}

	
	
}
