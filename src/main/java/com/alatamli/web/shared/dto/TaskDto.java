package com.alatamli.web.shared.dto;

import java.util.Date;

public class TaskDto {

	private long id;
	private int delayBetween;
	private String operationType;
	private boolean isStarted;
	private boolean isStoped;
	private Date lastExecute;
	private Date createdAt;
	private Date UpdatedAt;
	private InstanceDto instance;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getDelayBetween() {
		return delayBetween;
	}
	public void setDelayBetween(int delayBetween) {
		this.delayBetween = delayBetween;
	}
	public String getOperationType() {
		return operationType;
	}
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
	public boolean isStarted() {
		return isStarted;
	}
	public void setStarted(boolean isStarted) {
		this.isStarted = isStarted;
	}
	public boolean isStoped() {
		return isStoped;
	}
	public void setStoped(boolean isStoped) {
		this.isStoped = isStoped;
	}
	public Date getLastExecute() {
		return lastExecute;
	}
	public void setLastExecute(Date lastExecute) {
		this.lastExecute = lastExecute;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return UpdatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		UpdatedAt = updatedAt;
	}
	public InstanceDto getInstance() {
		return instance;
	}
	public void setInstance(InstanceDto instance) {
		this.instance = instance;
	}
	
	
}
