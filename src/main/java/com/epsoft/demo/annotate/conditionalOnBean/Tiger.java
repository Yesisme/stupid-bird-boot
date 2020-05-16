package com.epsoft.demo.annotate.conditionalOnBean;

public class Tiger {
	
	public Tiger(String age,String name,Zoo zoo) {
		this.age = age;
		this.name = name;
		this.zoo = zoo;
	}
	
	private Zoo zoo;
	
	private String name;
	
	private String age;

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

	public Zoo getZoo() {
		return zoo;
	}

	public void setZoo(Zoo zoo) {
		this.zoo = zoo;
	}
}
