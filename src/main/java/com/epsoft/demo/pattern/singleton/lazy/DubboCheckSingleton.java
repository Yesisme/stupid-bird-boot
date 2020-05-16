package com.epsoft.demo.pattern.singleton.lazy;

public class DubboCheckSingleton {

	private volatile static DubboCheckSingleton instance = null;
	private DubboCheckSingleton() {
		
	}
	
	public static DubboCheckSingleton getInstance () {
		if(instance== null) {
			synchronized(DubboCheckSingleton.class) {
				if(instance==null) {
					instance = new DubboCheckSingleton();
				}
			}
		}
		return instance;
	} 
	
}
