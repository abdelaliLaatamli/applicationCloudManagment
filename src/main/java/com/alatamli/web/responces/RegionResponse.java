package com.alatamli.web.responces;

public class RegionResponse {
	
	private long id ;
	private String regionCode;
	private String privateKey;
	private String publicKey;
	private String thumpPoint;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getRegionCode() {
		return regionCode;
	}
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
	public String getPrivateKey() {
		return privateKey;
	}
	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}
	public String getPublicKey() {
		return publicKey;
	}
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}
	public String getThumpPoint() {
		return thumpPoint;
	}
	public void setThumpPoint(String thumpPoint) {
		this.thumpPoint = thumpPoint;
	}
	
	

}
