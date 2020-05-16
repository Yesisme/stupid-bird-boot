package com.epsoft.demo.annotate.conditionalOnClass;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class ConditionalOnClassConfig {

	//classPath下没有kafka依赖所有没有注入
	@Bean(name="noAutowiredAuto")
	@ConditionalOnClass(name = "org.springframework.kafka.core.KafkaTemplate")
	public AutowiredAuto noAutowiredAuto() {
		return new AutowiredAuto();
	}
	
	//classPath引入了RedisTemplate依赖所以注入了
	@Bean(name="hasAutowiredAuto")
	@ConditionalOnClass(RedisTemplate.class)
	public AutowiredAuto hasAutowiredAuto() {
		return new AutowiredAuto();
	}
}
