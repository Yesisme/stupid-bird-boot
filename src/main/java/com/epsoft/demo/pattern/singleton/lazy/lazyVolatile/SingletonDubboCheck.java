package com.epsoft.demo.pattern.singleton.lazy.lazyVolatile;

public class SingletonDubboCheck {

	private volatile static SingletonDubboCheck instance = null;
	
	private SingletonDubboCheck() {
		
	}
	
	public static SingletonDubboCheck getInstance() {
		//这个判断的作用，可以给代码少上很多锁。不会执行到synchronized代码块
		if(instance==null) {
			synchronized(SingletonDubboCheck.class) {
				if(instance==null) {
					instance = new SingletonDubboCheck();
				}
			}
		}
		return instance;
	}
}
