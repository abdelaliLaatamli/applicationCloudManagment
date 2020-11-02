package com.alatamli.web.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("IOR")
public class InstanceOracle extends InstanceEntity {

	private String subnetId;
	private String vcnId;
	
	@OneToMany(cascade = CascadeType.ALL , mappedBy = "instance")
	private List<VcnEntity> vcns;
	
	
	public List<VcnEntity> getVcns() {
		return vcns;
	}
	public void setVcns(List<VcnEntity> vcns) {
		this.vcns = vcns;
	}
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
