package com.epsoft.demo.annotate.dependsOn;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;

@Configuration
public class AppConfig {

	@Bean(initMethod="initialize")
	@DependsOn("eventListener")
	public EventPublisherBean eventPublisher() {
		return new EventPublisherBean();
	}
	
	@Bean(name="eventListener",initMethod="initialize")
	//EventListenerBean在启动阶段不加载，当其他bean需要其时才加载
	@Lazy
	public EventListenerBean eventListenerBean() {
		return new EventListenerBean();
	}
	
	public static void main(String[] args) {
		new AnnotationConfigApplicationContext(AppConfig.class);
	}
}
