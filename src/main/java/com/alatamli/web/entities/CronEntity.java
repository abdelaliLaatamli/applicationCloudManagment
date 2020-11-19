package com.alatamli.web.entities;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "crons")
public class CronEntity {

	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable = false)
	private int delayBetween;
	
	@Column(nullable = false)
	private String operationType;
	
	@Column(nullable = false)
	private boolean isStarted = false;
	
	@Column(nullable = false)
	private boolean isStoped  = false;
	
	@Column(nullable = true)
	private Instant lastExecute;
	
	@Column( nullable = false )
	private Instant createdAt = Instant.now();
	
	@Column( nullable = false)
	private Instant UpdatedAt;
	

	@ManyToOne
	@JoinColumn(name = "instance_id")
	private InstanceEntity instance;

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


	public InstanceEntity getInstance() {
		return instance;
	}

	public void setInstance(InstanceEntity instance) {
		this.instance = instance;
	}
	
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
	
	
}
