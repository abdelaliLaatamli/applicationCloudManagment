package com.alatamli.web.helpers.responses;

import com.alatamli.web.responses.InstanceResponse;

public abstract class InstanceResponseHttp  {

	
	protected InstanceResponse database ;


	public InstanceResponse getDatabase() {
		return database;
	}


	public void setDatabase(InstanceResponse database) {
		this.database = database;
	}
	
}
