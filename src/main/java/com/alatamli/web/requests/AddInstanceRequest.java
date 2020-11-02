package com.alatamli.web.requests;

public class AddInstanceRequest {
	
	private String name ; 
	private int	numberInstances ; 
	private String region ; 
	private	String vmtaDomain;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNumberInstances() {
		return numberInstances;
	}
	public void setNumberInstances(int numberInstances) {
		this.numberInstances = numberInstances;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getVmtaDomain() {
		return vmtaDomain;
	}
	public void setVmtaDomain(String vmtaDomain) {
		this.vmtaDomain = vmtaDomain;
	}
	
	

}
