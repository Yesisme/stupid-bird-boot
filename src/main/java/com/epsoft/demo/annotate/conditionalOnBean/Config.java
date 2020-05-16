package com.epsoft.demo.annotate.conditionalOnBean;

import com.epsoft.demo.annotation.LymAutowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
	
	@Bean
	public Zoo zoo() {
		Zoo z = new Zoo("成都","大熊猫动物园");
		return z;
	}
	
	//spring容器中如果没有id为zoo的bean,则bean也不实例化注入容器
	@Bean
	@ConditionalOnBean(name="zoo",annotation = LymAutowired.class)
	public Tiger tiger(Zoo zoo) {
		Tiger t = new Tiger("2","跳跳虎",zoo);	
		return t;
	}



}
