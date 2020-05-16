package com.epsoft.demo.pattern.singleton.reflect;

import java.lang.reflect.Constructor;

public class Test {

	public static void main(String[] args) throws Exception {
		
		System.out.println("=============饿汉式============");
		//饿汉式
		//反射创建对象破坏单例模式。无法保证只有一个对象
		HungrySingleton instance = HungrySingleton.getInstance();
		Constructor<HungrySingleton> c = HungrySingleton.class.getDeclaredConstructor();
		c.setAccessible(true);
		HungrySingleton newInstance = c.newInstance();
		System.out.println("instance" +instance);
		System.out.println("newInstance" +newInstance);
		System.out.println(instance == newInstance);
		
		//饿汉式
		//解决如果构造器要反射创建一个对象就抛出一个异常
		HungrySingletonThrowException instance1 = HungrySingletonThrowException.getInstance();
		Constructor<HungrySingletonThrowException> c1 = HungrySingletonThrowException.class.getDeclaredConstructor();
		c1.setAccessible(true);
		HungrySingletonThrowException newInstance1 =null;
		try {
			newInstance1 = c1.newInstance();
		}catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		System.out.println("instance1" +instance1);
		System.out.println("newInstance1" +newInstance1);
		System.out.println(instance1 == newInstance1);
		
		
		System.out.println("=============懒汉式============");
		//懒汉式反射创建对象破坏单例模式。无法保证只有一个对象
		LazySingleton instance2 = LazySingleton.getInstance();
		Constructor  c2 = LazySingleton.class.getDeclaredConstructor();
		c2.setAccessible(true);
		LazySingleton newInstance2 = (LazySingleton) c2.newInstance();
		System.out.println(instance2);
		System.out.println(newInstance2);
		System.out.println(instance2 == newInstance2);
		
		
	   //懒汉式
	   //解决如果构造器要反射创建一个对象就抛出一个异常
	}
}
