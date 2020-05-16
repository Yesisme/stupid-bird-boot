package com.epsoft.demo.pattern.singleton.reflect;

public class LazySingletonThrowException {

	private static LazySingletonThrowException instance = null;
	
	private boolean flag = true;
	private LazySingletonThrowException(){
		if(flag) {
			flag=false;
		}else {
			throw new RuntimeException("单例禁止反射调用");
		}
	}
	
	public synchronized static LazySingletonThrowException getInstance() {
		if(instance == null) {
			instance = new LazySingletonThrowException();
		}
		return instance;
	}
}
