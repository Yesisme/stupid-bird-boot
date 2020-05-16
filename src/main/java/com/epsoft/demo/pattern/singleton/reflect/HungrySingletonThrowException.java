package com.epsoft.demo.pattern.singleton.reflect;

import java.io.Serializable;

/**
 * 目的 反射破坏单例模式，无法保证单例的唯一性
 * @author hp
 */
public class HungrySingletonThrowException implements Serializable{

	private static HungrySingletonThrowException instance = null;
	
	static {
		instance = new HungrySingletonThrowException();
	}
	
	private HungrySingletonThrowException() {
		if(instance!=null) {
			throw new RuntimeException("禁止反射调用");
		}
	}
	
	public static HungrySingletonThrowException getInstance() {
		return instance;
	}
	
	private Object readResolve() {
		return instance;
	}
}
