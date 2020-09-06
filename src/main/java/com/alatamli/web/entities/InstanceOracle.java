package com.alatamli.web.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("IOR")
public class InstanceOracle extends InstanceEntity {

	private String subnetId;
	private String vcnId;
	
	public String getSubnetId() {
		return subnetId;
	}
	public void setSubnetId(String subnetId) {
		this.subnetId = subnetId;
	}
	public String getVcnId() {
		return vcnId;
	}
	public void setVcnId(String vcnId) {
		this.vcnId = vcnId;
	}
	
}
