package com.epsoft.demo.pattern.singleton.lazy.lazySynchronized;

public class R implements Runnable{

	@Override
	public void run() {
		LazySingleton instance = LazySingleton.getInstance();
		System.out.println(Thread.currentThread().getName()+":"+instance);		
	}

}
