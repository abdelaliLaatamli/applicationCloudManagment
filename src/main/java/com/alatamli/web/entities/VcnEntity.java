package com.alatamli.web.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "vcns")
public class VcnEntity {

	@Id
	@GeneratedValue
	private long id;
	
	@Column
	private String vcnId;
	
	@Column(nullable = false )
	private String cidrBlock;
	
	@Column(nullable = false , length = 20)
	private String lanstSubnet;
	
	@Column
	private String routeTableId;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getVcnId() {
		return vcnId;
	}

	public void setVcnId(String vcnId) {
		this.vcnId = vcnId;
	}

	public String getCidrBlock() {
		return cidrBlock;
	}

	public void setCidrBlock(String cidrBlock) {
		this.cidrBlock = cidrBlock;
	}

	public String getLanstSubnet() {
		return lanstSubnet;
	}

	public void setLanstSubnet(String lanstSubnet) {
		this.lanstSubnet = lanstSubnet;
	}

	public String getRouteTableId() {
		return routeTableId;
	}

	public void setRouteTableId(String routeTableId) {
		this.routeTableId = routeTableId;
	}

	public String getSecurityListId() {
		return securityListId;
	}

	public void setSecurityListId(String securityListId) {
		this.securityListId = securityListId;
	}

	public InstanceEntity getInstance() {
		return instance;
	}

	public void setInstance(InstanceEntity instance) {
		this.instance = instance;
	}

	@Column
	private String securityListId;
	
	@ManyToOne
	@JoinColumn(name = "instance_id")
	private InstanceEntity instance;
	
	
}
