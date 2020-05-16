package com.epsoft.demo.pattern.singleton.reflect;

import java.io.Serializable;

/**
 * 目的 反射破坏单例模式，无法保证单例的唯一性
 * @author hp
 */
public class HungrySingleton implements Serializable{

	private static HungrySingleton instance = null;
	
	static {
		instance = new HungrySingleton();
	}
	
	private HungrySingleton() {
		
	}
	
	public static HungrySingleton getInstance() {
		return instance;
	}
	
	private Object readResolve() {
		return instance;
	}
}
