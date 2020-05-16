package com.epsoft.demo.pattern.factory.abstartctFactory;

public class PythonCourseFactory implements CourseFactory{

	@Override
	public Video getVideo() {
		return new PythonVideo();
	}

	@Override
	public Article getArticle() {
		return new PythonArticle();
	}

}
