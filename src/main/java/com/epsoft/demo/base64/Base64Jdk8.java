package com.epsoft.demo.base64;

import java.util.Base64;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class Base64Jdk8 {

	public static void main(String[] args) throws Exception {
		String encodeToString = Base64.getEncoder().encodeToString("new/teacher=liming".getBytes("utf-8"));
		log.info("基本编码:{}",encodeToString);
		byte[] bytes = Base64.getDecoder().decode(encodeToString);
		log.info("解码:{}",new String(bytes,"utf-8"));
	}
}
