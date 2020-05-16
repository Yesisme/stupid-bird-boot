package com.epsoft.demo.pattern.singleton.lazy;

public class LazySingleton {

	private static LazySingleton instance = null;
	
	private LazySingleton(){
		
	}
	
	public synchronized static LazySingleton getInstance() {
		if(instance == null) {
			instance = new LazySingleton();
		}
		return instance;
	}
}
