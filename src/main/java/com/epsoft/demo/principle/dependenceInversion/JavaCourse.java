package com.epsoft.demo.principle.dependenceInversion;

import org.springframework.stereotype.Service;

@Service
public class JavaCourse implements ICourse{

	@Override
	public void studyCourse() {
		System.out.println("学习java课程");
	}

}
