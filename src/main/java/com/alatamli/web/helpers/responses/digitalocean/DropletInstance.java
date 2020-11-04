package com.alatamli.web.helpers.responses.digitalocean;

import java.util.Date;

import com.alatamli.web.helpers.responses.InstanceResponse;

public class DropletInstance extends InstanceResponse  {

	private long id;
	private String name;
	private long memory;
	private int vcpus;
	private int disk;
	private boolean locked;
	private String status;
	private Date created_at;
	private Networks networks;
	private Object kernel;
	private String [] features ;
	private Object [] backup_ids;
	private Object next_backup_window;
	private Object [] snapshot_ids;
	private Object image; 
	private Object [] volume_ids;
	private Object size;
	private String size_slug;
	private Object region ;
	private Object [] tags;
    private String vpc_uuid;
 
	/*
    public Object getDatabase() {
		return database;
	}


	public void setDatabase(Object database) {
		this.database = database;
	}
*/

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public long getMemory() {
		return memory;
	}


	public void setMemory(long memory) {
		this.memory = memory;
	}


	public int getVcpus() {
		return vcpus;
	}


	public void setVcpus(int vcpus) {
		this.vcpus = vcpus;
	}


	public int getDisk() {
		return disk;
	}


	public void setDisk(int disk) {
		this.disk = disk;
	}


	public boolean isLocked() {
		return locked;
	}


	public void setLocked(boolean locked) {
		this.locked = locked;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Date getCreated_at() {
		return created_at;
	}


	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}


	public Object getKernel() {
		return kernel;
	}



	public void setKernel(Object kernel) {
		this.kernel = kernel;
	}


	public String[] getFeatures() {
		return features;
	}


	public void setFeatures(String[] features) {
		this.features = features;
	}


	public Object[] getBackup_ids() {
		return backup_ids;
	}


	public void setBackup_ids(Object[] backup_ids) {
		this.backup_ids = backup_ids;
	}


	public Object getNext_backup_window() {
		return next_backup_window;
	}


	public void setNext_backup_window(Object next_backup_window) {
		this.next_backup_window = next_backup_window;
	}


	public Object[] getSnapshot_ids() {
		return snapshot_ids;
	}


	public void setSnapshot_ids(Object[] snapshot_ids) {
		this.snapshot_ids = snapshot_ids;
	}


	public Object getImage() {
		return image;
	}


	public void setImage(Object image) {
		this.image = image;
	}


	public Object[] getVolume_ids() {
		return volume_ids;
	}


	public void setVolume_ids(Object[] volume_ids) {
		this.volume_ids = volume_ids;
	}


	public Object getSize() {
		return size;
	}


	public void setSize(Object size) {
		this.size = size;
	}


	public String getSize_slug() {
		return size_slug;
	}


	public void setSize_slug(String size_slug) {
		this.size_slug = size_slug;
	}


	public Object getRegion() {
		return region;
	}


	public void setRegion(Object region) {
		this.region = region;
	}


	public Object[] getTags() {
		return tags;
	}


	public void setTags(Object[] tags) {
		this.tags = tags;
	}


	public String getVpc_uuid() {
		return vpc_uuid;
	}


	public void setVpc_uuid(String vpc_uuid) {
		this.vpc_uuid = vpc_uuid;
	}


	public Networks getNetworks() {
		return networks;
	}


	public void setNetworks(Networks networks) {
		this.networks = networks;
	}
    

}
