package com.epsoft.demo.annotation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.epsoft.demo.SpringBootDemoApplication;
import com.epsoft.demo.annotate.conditionalOnClass.AutowiredAuto;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootDemoApplication.class)
public class ConditionalOnClassTest {

	 @Autowired(required=false)
	 @Qualifier(value="noAutowiredAuto")
	 private AutowiredAuto autowiredAuto;
	 
	 
	 @Autowired(required=false)
	 @Qualifier(value="hasAutowiredAuto")
	 private AutowiredAuto hasAutowiredAuto;
	 
	 @Test
	 public void testConditionalOnClass() {
		 System.out.println("================="+autowiredAuto+"===============");
		 System.out.println("================="+hasAutowiredAuto+"===============");
		 
	 }
}
