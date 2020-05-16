package com.epsoft.demo.pattern.factory.factroyMethod;

public class JavaVideo implements Video{

	@Override
	public void produce() {
		System.out.println("这是java");
	}

	@Override
	public String trade(String id) {
		return "java交易"+id+"名称";
	}

	
}
