package com.epsoft.demo.annotate.conditionalOnBean;

import com.epsoft.demo.annotation.LymAutowired;
@LymAutowired
public class Zoo {

	public Zoo(String city,String name) {
		this.city = city;
		this.name = name;
	}
	
	private String city;
	
	private String name;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
