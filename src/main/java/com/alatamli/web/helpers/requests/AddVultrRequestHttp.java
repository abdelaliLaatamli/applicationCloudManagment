package com.alatamli.web.helpers.requests;

public class AddVultrRequestHttp {
	
	
	private String region;
	private String plan = "vc2-1c-1gb"; 
	private String label;
	//private int os_id = 127;
	private int os_id = 167;
	private String backups = "disabled" ;
	
	
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getPlan() {
		return plan;
	}
	public void setPlan(String plan) {
		this.plan = plan;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public int getOs_id() {
		return os_id;
	}
	public void setOs_id(int os_id) {
		this.os_id = os_id;
	}
	public String getBackups() {
		return backups;
	}
	public void setBackups(String backups) {
		this.backups = backups;
	} 
		
	

}
