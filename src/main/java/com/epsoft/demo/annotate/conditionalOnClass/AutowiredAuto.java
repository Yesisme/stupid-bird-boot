package com.epsoft.demo.annotate.conditionalOnClass;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;



public class AutowiredAuto {

	private String name;
	
	public String getName() {
		return name;
	}

	public AutowiredAuto() {
		super();
		
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
