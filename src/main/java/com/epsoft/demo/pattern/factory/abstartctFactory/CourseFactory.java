package com.epsoft.demo.pattern.factory.abstartctFactory;

/**
 * @author Administrato
 * 产品等级结构 JavaCoureseFactory  PythonCoureseFactory
 */
public interface CourseFactory {

	Video getVideo();
	
	Article getArticle();
}
