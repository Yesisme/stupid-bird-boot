package com.epsoft.demo.jdk8;

import com.epsoft.demo.bean.entity.User;

public interface Predicate<User> {

	boolean filter(User user);
}
