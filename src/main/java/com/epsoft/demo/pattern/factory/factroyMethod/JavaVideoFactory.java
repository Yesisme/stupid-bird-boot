package com.epsoft.demo.pattern.factory.factroyMethod;

public class JavaVideoFactory extends VideoFactory{

	@Override
	public Video getVideo() {
		
		return new JavaVideo();
	}

}
