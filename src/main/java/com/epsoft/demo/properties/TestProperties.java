package com.epsoft.demo.properties;

import java.io.IOException;

import org.apache.maven.wagon.resource.Resource;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.PathResource;

class TestPropertiesClass{
	public static void testProperties() throws IOException {
		PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
		propertiesFactoryBean.setLocations(new ClassPathResource("test.properties"));
		propertiesFactoryBean.afterPropertiesSet();
		System.out.println(propertiesFactoryBean.getObject());
	}
	
	
}
public class TestProperties {

	public static void main(String[] args) throws IOException {
		TestPropertiesClass.testProperties();

	}

}
