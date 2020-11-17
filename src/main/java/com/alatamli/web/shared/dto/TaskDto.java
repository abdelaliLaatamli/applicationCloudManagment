package com.alatamli.web.shared.dto;

import java.time.Instant;

public class TaskDto {

	private long id;
	private int delayBetween;
	private String operationType;
	private boolean isStarted;
	private boolean isStoped;
	private Instant lastExecute;
	private Instant createdAt;
	private Instant UpdatedAt;
	private InstanceDto instance;
	
	
	public Instant getLastExecute() {
		return lastExecute;
	}
	public void setLastExecute(Instant lastExecute) {
		this.lastExecute = lastExecute;
	}
	public Instant getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}
	public Instant getUpdatedAt() {
		return UpdatedAt;
	}
	public void setUpdatedAt(Instant updatedAt) {
		UpdatedAt = updatedAt;
	}
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
	public InstanceDto getInstance() {
		return instance;
	}
	public void setInstance(InstanceDto instance) {
		this.instance = instance;
	}
	
	
}
