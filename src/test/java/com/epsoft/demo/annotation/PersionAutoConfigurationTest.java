package com.epsoft.demo.annotation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.epsoft.demo.SpringBootDemoApplication;
import com.epsoft.demo.annotate.enableConfigurationProperties.PersionServiceProperties;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootDemoApplication.class)
public class PersionAutoConfigurationTest {
	
	@Autowired(required=false)
	private PersionServiceProperties psp;

	//注释掉EnableConfigurationProperties报null
	@Test
	public void testHasEnableConfigurationProperties() {
		System.out.println("============="+psp.getName()+"================");
	}
}
