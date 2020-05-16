package com.epsoft.demo.pattern.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


public class FinshSellMan implements InvocationHandler {
	
	private Animal animal;
	
	//获取被代理人个人资料
	public Object getInstance(Animal target) {
		this.animal=target;
		Class class1 = target.getClass();
		return Proxy.newProxyInstance(class1.getClassLoader(), class1.getInterfaces(),this);
	}

	@Override
	public Object invoke(Object obj, Method method, Object[] arg2) throws Throwable {
		System.out.println("我是鱼饭");
		System.out.println("你的年龄是"+this.animal.getAge()+"我得给你找一条小鱼");
		this.animal.eat();
		System.out.println("准备吃鱼");
		return null;
	}
	
	public static void main(String[] args) {
		Animal animal = (Animal) new FinshSellMan().getInstance(new Cat());
		animal.eat();
	}

}
