package com.epsoft.demo.jdk8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
public class TestForEach {

	public static void main(String[] args) {
		List<String> list = Arrays.asList("1","2","4","6","8");
		//java8通过匿名内部类实现遍历循环
		list.forEach(new Consumer<String>() {
			@Override
			public void accept(String t) {
				System.out.println(t);
			}
		});
		//lanmada表达式
		list.stream().forEach((String i)->{System.out.println(Integer.valueOf(i)>8);});

		//方法引用
		list.stream().forEach(System.out::println);
		

	}
}
