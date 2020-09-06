package com.alatamli.web.entities;

import java.util.Date;

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
	private Date lastExecute;
	
	@Column( nullable = false )
	private Date createdAt;
	
	@Column( nullable = false)
	private Date UpdatedAt;
	
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

	public InstanceEntity getInstance() {
		return instance;
	}

	public void setInstance(InstanceEntity instance) {
		this.instance = instance;
	}
	
	
}
