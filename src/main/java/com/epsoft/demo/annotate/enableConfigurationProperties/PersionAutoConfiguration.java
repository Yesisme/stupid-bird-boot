package com.epsoft.demo.annotate.enableConfigurationProperties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(PersionServiceProperties.class)
public class PersionAutoConfiguration {
	

}
