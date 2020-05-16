package com.epsoft.demo.jdk8;

import com.alibaba.fastjson.JSON;

public class FanXig<T> {

	private String name;
	
	private T data;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	public static void main(String[] args) {
		FanXig f = new FanXig();
		f.setData(23);
		f.setName("name");
		System.out.println(JSON.toJSONString(f).toString());
	}
}
