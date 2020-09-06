package com.alatamli.web.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("IAZ")
public class InstanceAzure extends InstanceEntity {

	@Column
	private boolean isConfigured = false;

	public boolean isConfigured() {
		return isConfigured;
	}

	public void setConfigured(boolean isConfigured) {
		this.isConfigured = isConfigured;
	}
	
}
