package com.epsoft.demo.pattern.factory.abstartctFactory;

public class test {

	public static void main(String[] args) {
		CourseFactory javaFactory = new JavaCourseFactory();
		javaFactory.getArticle().produce();
		javaFactory.getVideo().produce();
	}
	
}
