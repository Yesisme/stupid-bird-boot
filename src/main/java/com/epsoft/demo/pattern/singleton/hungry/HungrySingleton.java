package com.epsoft.demo.pattern.singleton.hungry;

import java.io.Serializable;

public class HungrySingleton implements Serializable{

	private static final HungrySingleton instance;
	
	static {
		instance = new HungrySingleton();
	}
	
	private HungrySingleton() {
		
	}
	
	public static HungrySingleton getInstance() {
		return instance;
	}
}
