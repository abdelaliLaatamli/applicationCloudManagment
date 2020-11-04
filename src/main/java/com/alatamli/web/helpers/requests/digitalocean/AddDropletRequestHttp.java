package com.alatamli.web.helpers.requests.digitalocean;

import java.util.List;

public class AddDropletRequestHttp {
	

	private List<String> names ;
	private String region ;
    private String size = "s-1vcpu-1gb" ;
    private String image = "centos-6-x64" ;
    private String [] ssh_keys = {} ;
    private boolean backups = false;
    private boolean ipv6  = false ;
    private String user_data = null;
    private boolean private_networking=  true;
    private String volumes = null ;
    private String [] tags = {"web"};
    
    public List<String> getNames() {
		return names;
	}
	public void setNames(List<String> names) {
		this.names = names;
	} 
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String[] getSsh_keys() {
		return ssh_keys;
	}
	public void setSsh_keys(String[] ssh_keys) {
		this.ssh_keys = ssh_keys;
	}
	public boolean isBackups() {
		return backups;
	}
	public void setBackups(boolean backups) {
		this.backups = backups;
	}
	public boolean isIpv6() {
		return ipv6;
	}
	public void setIpv6(boolean ipv6) {
		this.ipv6 = ipv6;
	}
	public String getUser_data() {
		return user_data;
	}
	public void setUser_data(String user_data) {
		this.user_data = user_data;
	}
	public boolean isPrivate_networking() {
		return private_networking;
	}
	public void setPrivate_networking(boolean private_networking) {
		this.private_networking = private_networking;
	}
	public String getVolumes() {
		return volumes;
	}
	public void setVolumes(String volumes) {
		this.volumes = volumes;
	}
	public String[] getTags() {
		return tags;
	}
	public void setTags(String[] tags) {
		this.tags = tags;
	}
	

}
