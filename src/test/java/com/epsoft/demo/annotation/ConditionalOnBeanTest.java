package com.epsoft.demo.annotation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.epsoft.demo.SpringBootDemoApplication;
import com.epsoft.demo.annotate.conditionalOnBean.Tiger;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootDemoApplication.class)
public class ConditionalOnBeanTest {

	@Autowired(required=false)
	private Tiger tiger;
	
	@Test
	public void testNoAnnotation() {
		System.out.println(tiger.getName()+tiger.getAge()+"岁了,"+"住在"+tiger.getZoo().getCity()+tiger.getZoo().getName());
	}
	
	@Test
	public void testHasConditionalOnBean() {
		System.out.println("==============="+tiger+"==============");
			
	}
	
}
