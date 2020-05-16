package com.epsoft.demo.utils;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

@Configuration
public class WebConfigBean{
	@Autowired
	private RequestMappingHandlerAdapter handlerAdapter;
	
	@PostConstruct
	public void initEditableAvlidation() {
		ConfigurableWebBindingInitializer webBindingInitializer = 
				(ConfigurableWebBindingInitializer) handlerAdapter.getWebBindingInitializer();
		if(webBindingInitializer.getConversionService()!=null){
			GenericConversionService GenericConversionService = 
					(org.springframework.core.convert.support.GenericConversionService) webBindingInitializer.getConversionService();
			GenericConversionService.addConverter(new StringToDateConverter());
		}
		
	}
	
	
}
