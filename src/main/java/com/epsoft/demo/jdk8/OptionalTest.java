package com.epsoft.demo.jdk8;

import java.util.Arrays;
import java.util.List;

public class OptionalTest {

	public static void main(String[] args) {
		
		List<String> list = Arrays.asList("hello","world","worldhello");
		
		list.stream().mapToInt(item->item.length()).filter(length->length==5).findFirst().ifPresent(System.out::println);
		
		
	}

}
