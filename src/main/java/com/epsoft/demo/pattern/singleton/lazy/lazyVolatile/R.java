package com.epsoft.demo.pattern.singleton.lazy.lazyVolatile;

public class R implements Runnable{

	@Override
	public void run() {
		SingletonDubboCheck instance = SingletonDubboCheck.getInstance();
		System.out.println(Thread.currentThread().getName()+":"+instance);		
	}

}
