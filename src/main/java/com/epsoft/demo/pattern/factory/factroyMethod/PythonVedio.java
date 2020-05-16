package com.epsoft.demo.pattern.factory.factroyMethod;

public class PythonVedio implements Video{

	@Override
	public void produce() {
		System.out.println("这是python视频");
	}

	@Override
	public String trade(String id) {
		return "py交易"+id+"名称";
	}

	



}
