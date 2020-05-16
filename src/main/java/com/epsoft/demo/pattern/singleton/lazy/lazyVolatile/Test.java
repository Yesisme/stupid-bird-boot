package com.epsoft.demo.pattern.singleton.lazy.lazyVolatile;

public class Test {

	public static void main(String[] args) {
		R r = new R();
		for (int i = 0; i <20 ; i++) {
			new Thread(r).start();
		}
	}
}
