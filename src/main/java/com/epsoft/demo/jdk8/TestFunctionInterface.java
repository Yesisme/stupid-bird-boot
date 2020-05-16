package com.epsoft.demo.jdk8;

import java.util.function.Function;

@FunctionalInterface //声明这是一个函数式接口，只能有一个抽象方法
interface MyInterface {

	void test();
	
	String toString();//toString默认是继承的Object方法，所有只有一个抽象方法
}

public class TestFunctionInterface {

	public void testMyInterface(MyInterface myInterface) {
		System.out.println(1);
		myInterface.test();
		System.out.println(2);
	}
	
	public int testFunction(int a,Function<Integer, Integer> function1,Function<Integer, Integer> function2) {
		return function1.compose(function2).apply(a);
	}
	
	
	public static void main(String[] args) {
		TestFunctionInterface fi = new TestFunctionInterface();
		//传统方法调用直接new一个匿名内部类
		fi.testMyInterface(new MyInterface() {
			@Override
			public void test() {
				System.out.println("test");
				
			}
		});
		
		//java8通过lanmada函数实现,不需要知道传什么方法 
		fi.testMyInterface(()->{
			System.out.println("java8Test");
		});
		
		MyInterface interface1 = ( () ->{
			System.out.println("java8othersTest");
		});
	}
	
}


