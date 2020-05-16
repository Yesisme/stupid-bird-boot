package com.epsoft.demo.principle.dependenceInversion;

public class Test {

	public static void main(String[] args) {
		
		//构造方法注入测试，需要一直new Leym对象
		/*Leym le = new Leym(new JavaCourse());
		le.StudyPrincipleDependenceInversion();*/
		
		//set方法注入实现
		/*Leym le = new Leym();
		le.setiCourse(new JavaCourse());
		le.StudyPrincipleDependenceInversion();*/
		

	}  
}
