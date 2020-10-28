package com.alatamli.web.responses;

import java.util.List;


public class AccountResponseTwoKeysDetails extends AccountResponseTwoKeys {
	private List<RegionResponse> regions ;

	public List<RegionResponse> getRegions() {
		return regions;
	}

	public void setRegions(List<RegionResponse> regions) {
		this.regions = regions;
	}
}
