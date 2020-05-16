package com.epsoft.demo.pattern.factory.abstartctFactory;

public class PythonArticle extends Article{

	@Override
	public void produce() {
		System.out.println("是py的article");
	}
}
