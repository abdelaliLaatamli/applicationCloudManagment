package com.alatamli.web.helpers.responses.digitalocean;

import java.util.List;

public class Networks {

	private List<Object> v6;
	private List<NetworksV4> v4;

	
	public List<Object> getV6() {
		return v6;
	}

	public void setV6(List<Object> v6) {
		this.v6 = v6;
	}

	public List<NetworksV4> getV4() {
		return v4;
	}

	public void setV4(List<NetworksV4> v4) {
		this.v4 = v4;
	}


}
