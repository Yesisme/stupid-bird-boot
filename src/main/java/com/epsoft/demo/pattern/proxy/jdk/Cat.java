package com.epsoft.demo.pattern.proxy.jdk;

public class Cat implements Animal{

	private String name="白猫";
	
	private String age="3";
	
	@Override
	public void eat() {
		System.out.println("我叫"+this.name+",我的年龄是"+this.age);
		System.out.println("我喜欢吃鱼");
		System.out.println("鱼要熟");
		System.out.println("如果有我马上就买");
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
}
