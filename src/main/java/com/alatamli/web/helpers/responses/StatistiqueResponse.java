package com.alatamli.web.helpers.responses;

import java.util.ArrayList;
import java.util.List;

public class StatistiqueResponse {
	
	private String name ;
	private List<Integer> values = new ArrayList<>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Integer> getValues() {
		return values;
	}
	public void setValues(List<Integer> values) {
		this.values = values;
	}

}
