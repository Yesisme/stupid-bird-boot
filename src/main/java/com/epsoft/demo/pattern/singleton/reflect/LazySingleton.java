package com.epsoft.demo.pattern.singleton.reflect;

public class LazySingleton {

	private static LazySingleton instance = null;
	
	private static boolean flag = true;
	private LazySingleton(){
		if(flag) {
			flag=false;
		}else {
			throw new RuntimeException("单例禁止反射调用");
		}
	}
	
	public synchronized static LazySingleton getInstance() {
		if(instance == null) {
			instance = new LazySingleton();
		}
		return instance;
	}
}
