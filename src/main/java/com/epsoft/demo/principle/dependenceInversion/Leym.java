package com.epsoft.demo.principle.dependenceInversion;
/**
 * 依赖倒置，面向接口编程,高层不依赖底层
 * @author hp
 *可以实现构造器注入，或者set注入。leym和ICourse都不需要更改，只需要增加课程的实现
 */

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Leym {

	/*private ICourse iCourse;
	
	//构造方法注入
	public Leym(ICourse iCourse) {
		this.iCourse = iCourse;
	}
	
	public void StudyPrincipleDependenceInversion() {
		iCourse.studyCourse();
	}*/
	

	private ICourse iCourse;
	
	//set方法注入
	public void setiCourse(ICourse iCourse) {
		this.iCourse = iCourse;
	}
	
	public void StudyPrincipleDependenceInversion() {
		iCourse.studyCourse();
	}

}
