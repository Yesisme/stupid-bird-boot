package com.epsoft.demo.jdk8;

import java.util.ArrayList;
import java.util.List;

import com.epsoft.demo.bean.entity.User;

public class filterApple {
	
	//java8实现 参数传入接口名 
	public static List<User> filterApples(List<User> invertory, Predicate<User> p) {
		List<User> result = new ArrayList<>();
		for (User user : invertory) {
			if (p.filter(user)) {
				result.add(user);
			}
		}
		return result;
	}
	
	//传统实现参数传入实现类
	public static List<User> filterApples(List<User> invertory, PredicateUser p) {
		List<User> result = new ArrayList<>();
		for (User user : invertory) {
			if (p.filter(user)) {
				result.add(user);
			}
		}
		return result;
	}
	
	public static boolean isGreen(User user) {
		return "男".equals(user.getSex());
	}

	public static boolean isAgeHigh(User user) {
		return user.getAge() > 22;
	}

	
}
