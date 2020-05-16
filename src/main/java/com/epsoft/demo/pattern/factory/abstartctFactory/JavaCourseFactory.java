package com.epsoft.demo.pattern.factory.abstartctFactory;
/**
 * 产品族
 * @author Administrator
 *
 */
public class JavaCourseFactory implements CourseFactory{

	@Override
	public Video getVideo() {
		return new JavaVideo();
	}

	@Override
	public Article getArticle() {
		return new JavaArticle();
	}

}
