package com.epsoft.demo.jdk8;

import com.epsoft.demo.bean.entity.User;

public class PredicateUser{

	//传统实现需要继承接口Predicate
	/*@Override
	public boolean filter(User user) {
		return user.getAge()>30&&user.getSex().toString().equals("男");	
	}*/
	
	//java8实现不需要继承接口
	public static  boolean filter(User user) {
		return user.getAge()>30&&user.getSex().toString().equals("男");	
	}
	
}
