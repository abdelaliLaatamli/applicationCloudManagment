package com.alatamli.web.requests;

public class AccountActionsRequest {
	
	private long userId ; 
    private String action;
	
    public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	

    
}
