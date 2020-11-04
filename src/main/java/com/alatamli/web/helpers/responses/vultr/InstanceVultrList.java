package com.alatamli.web.helpers.responses.vultr;

import java.util.List;

public class InstanceVultrList {

	private List<InstanceVultr> instances ;
	private Object meta;
	
	public List<InstanceVultr> getInstances() {
		return instances;
	}
	public void setInstances(List<InstanceVultr> instances) {
		this.instances = instances;
	}
	public Object getMeta() {
		return meta;
	}
	public void setMeta(Object meta) {
		this.meta = meta;
	}
	
	
	
	
}
