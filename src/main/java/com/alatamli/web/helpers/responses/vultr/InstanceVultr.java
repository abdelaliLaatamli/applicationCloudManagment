package com.alatamli.web.helpers.responses.vultr;

import com.alatamli.web.helpers.responses.InstanceResponse;

public class InstanceVultr extends InstanceResponse {
	
	private String id ;
    private String os ;
    private int ram ;
    private int disk ; 
    private String main_ip ; 
    private int vcpu_count;
    private String region;
    private String plan;
    private String date_created;
    private String status;
    private int allowed_bandwidth;
    private String netmask_v4;
    private String gateway_v4;
    private String power_status;
    private String server_status;
    private String v6_network;
    private String v6_main_ip;
    private int v6_network_size;
    private String label;
    private String internal_ip;
    private String kvm ;
    private String tag;
    private int os_id;
    private int app_id;
    private String firewall_group_id;
    private Object [] features;
    private String default_password;
    
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public int getRam() {
		return ram;
	}
	public void setRam(int ram) {
		this.ram = ram;
	}
	public int getDisk() {
		return disk;
	}
	public void setDisk(int disk) {
		this.disk = disk;
	}
	public String getMain_ip() {
		return main_ip;
	}
	public void setMain_ip(String main_ip) {
		this.main_ip = main_ip;
	}
	public int getVcpu_count() {
		return vcpu_count;
	}
	public void setVcpu_count(int vcpu_count) {
		this.vcpu_count = vcpu_count;
	}
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
	public String getDate_created() {
		return date_created;
	}
	public void setDate_created(String date_created) {
		this.date_created = date_created;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getAllowed_bandwidth() {
		return allowed_bandwidth;
	}
	public void setAllowed_bandwidth(int allowed_bandwidth) {
		this.allowed_bandwidth = allowed_bandwidth;
	}
	public String getNetmask_v4() {
		return netmask_v4;
	}
	public void setNetmask_v4(String netmask_v4) {
		this.netmask_v4 = netmask_v4;
	}
	public String getGateway_v4() {
		return gateway_v4;
	}
	public void setGateway_v4(String gateway_v4) {
		this.gateway_v4 = gateway_v4;
	}
	public String getPower_status() {
		return power_status;
	}
	public void setPower_status(String power_status) {
		this.power_status = power_status;
	}
	public String getServer_status() {
		return server_status;
	}
	public void setServer_status(String server_status) {
		this.server_status = server_status;
	}
	public String getV6_network() {
		return v6_network;
	}
	public void setV6_network(String v6_network) {
		this.v6_network = v6_network;
	}
	public String getV6_main_ip() {
		return v6_main_ip;
	}
	public void setV6_main_ip(String v6_main_ip) {
		this.v6_main_ip = v6_main_ip;
	}
	public int getV6_network_size() {
		return v6_network_size;
	}
	public void setV6_network_size(int v6_network_size) {
		this.v6_network_size = v6_network_size;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getInternal_ip() {
		return internal_ip;
	}
	public void setInternal_ip(String internal_ip) {
		this.internal_ip = internal_ip;
	}
	public String getKvm() {
		return kvm;
	}
	public void setKvm(String kvm) {
		this.kvm = kvm;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public int getOs_id() {
		return os_id;
	}
	public void setOs_id(int os_id) {
		this.os_id = os_id;
	}
	public int getApp_id() {
		return app_id;
	}
	public void setApp_id(int app_id) {
		this.app_id = app_id;
	}
	public String getFirewall_group_id() {
		return firewall_group_id;
	}
	public void setFirewall_group_id(String firewall_group_id) {
		this.firewall_group_id = firewall_group_id;
	}
	public Object[] getFeatures() {
		return features;
	}
	public void setFeatures(Object[] features) {
		this.features = features;
	}
	public String getDefault_password() {
		return default_password;
	}
	public void setDefault_password(String default_password) {
		this.default_password = default_password;
	}

}
