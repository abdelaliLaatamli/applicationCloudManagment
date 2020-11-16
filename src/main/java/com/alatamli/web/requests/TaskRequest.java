package com.alatamli.web.requests;


public class TaskRequest {

	private int delayBetween;
	private String operationType;
	private int instance;
	
	
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
	public int getInstance() {
		return instance;
	}
	public void setInstance(int instance) {
		this.instance = instance;
	}
	
}
