package com.epsoft.demo.pattern.factory.factroyMethod;

public class Test {

	public static void main(String[] args) {
		VideoFactory javaV = new JavaVideoFactory();
		javaV.getVideo().produce();
		System.out.println(javaV.getVideo().trade("1"));
		
		VideoFactory py = new PythonVideoFactory();
		py.getVideo().produce();
		System.out.println(py.getVideo().trade("2"));
	}
}
