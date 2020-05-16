package com.epsoft.demo.pattern.factory.factroyMethod;

public class PythonVideoFactory extends VideoFactory{

	@Override
	public Video getVideo() {
		return new PythonVedio();
	}

}
