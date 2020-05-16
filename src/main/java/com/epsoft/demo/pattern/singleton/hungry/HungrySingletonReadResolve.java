package com.epsoft.demo.pattern.singleton.hungry;

import java.io.Serializable;

public class HungrySingletonReadResolve implements Serializable{

	private static HungrySingletonReadResolve instance = null;
	
	static {
		instance = new HungrySingletonReadResolve();
	}
	
	private HungrySingletonReadResolve() {
		
	}
	
	public static final HungrySingletonReadResolve getInstance() {
		return instance;
	}
	
	private Object readResolve() {
		return instance;	
	} 
}
