package com.epsoft.demo.pattern.singleton.lazy;

import com.epsoft.demo.pattern.singleton.reflect.LazySingleton;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class R implements Runnable{

	@Override
	public void run() {
		//懒汉式synchronized
		LazySingleton instance = LazySingleton.getInstance();
		log.info("synchronized:{}",Thread.currentThread().getName()+instance);
		//懒汉式双重校验，使用volatile禁止重排序
		DubboCheckSingleton instance2 = DubboCheckSingleton.getInstance();
		log.info("dubboCheck:{}",Thread.currentThread().getName()+instance2);
		
		//懒汉式之匿名内部内,不允许在另一个线程看到它的排序，在安全范围内等待
		
		
	}

}
