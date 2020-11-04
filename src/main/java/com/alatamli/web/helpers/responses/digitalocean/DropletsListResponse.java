package com.alatamli.web.helpers.responses.digitalocean;

import java.util.List;

import com.alatamli.web.helpers.responses.InstanceResponse;

public class DropletsListResponse {

	private List<DropletInstance> droplets ;
	private Object meta ;
	private Object links;
	
	public List<DropletInstance> getDroplets() {
		return droplets;
	}
	public void setDroplets(List<DropletInstance> droplets) {
		this.droplets = droplets;
	}
	
	public Object getMeta() {
		return meta;
	}
	public void setMeta(Object meta) {
		this.meta = meta;
	}
	public Object getLinks() {
		return links;
	}
	public void setLinks(Object links) {
		this.links = links;
	}
	
	
}
