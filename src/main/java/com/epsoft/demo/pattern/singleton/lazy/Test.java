package com.epsoft.demo.pattern.singleton.lazy;

public class Test {

	public static void main(String[] args) {
		Thread t1 = new Thread(new R());
		Thread t2 = new Thread(new R());
		t1.start();
		t2.start();
			
	}
}
