package com.alatamli.web.shared.dto;

import java.util.List;


public class AccountTwoKeysDto extends AccountDto {
	
	private String accessKey;
	private String secriteKey;
	private List<RegionDto> regions ;
	
	public String getAccessKey() {
		return accessKey;
	}
	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}
	public String getSecriteKey() {
		return secriteKey;
	}
	public void setSecriteKey(String secriteKey) {
		this.secriteKey = secriteKey;
	}
	public List<RegionDto> getRegions() {
		return regions;
	}
	public void setRegions(List<RegionDto> regions) {
		this.regions = regions;
	}
	

}
