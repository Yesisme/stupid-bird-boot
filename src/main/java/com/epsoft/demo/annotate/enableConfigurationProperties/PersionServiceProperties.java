package com.epsoft.demo.annotate.enableConfigurationProperties;

import org.springframework.boot.context.properties.ConfigurationProperties;

//不添加@Component注解 通过EnableConfigurationProperites统一注入到容器中
@ConfigurationProperties(prefix="persion")
public class PersionServiceProperties {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
