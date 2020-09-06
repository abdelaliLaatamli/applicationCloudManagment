package com.alatamli.web.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("A2K")
public class AccountTwoKeysEntity extends AccountEntity {

	@Column
	private String accessKey;
	
	
	@Column
	private String secriteKey;
	
	@OneToMany(cascade = CascadeType.ALL , mappedBy = "account")
	private List<RegionEntity> regions ;
	
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

	public List<RegionEntity> getRegions() {
		return regions;
	}

	public void setRegions(List<RegionEntity> regions) {
		this.regions = regions;
	}


	
}
