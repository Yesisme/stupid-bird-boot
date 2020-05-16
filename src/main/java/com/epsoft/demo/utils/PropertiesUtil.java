package com.epsoft.demo.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import org.codehaus.plexus.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PropertiesUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

	private static Properties propes;
	
	static{
		String fileName = "ftp.properties";
		propes = new Properties();
		try{
			propes.load(new InputStreamReader(PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName), "UTF-8"));
			logger.info("============",new InputStreamReader(PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName),"UTF-8"));
		}catch(Exception e){
			logger.info("加载异常",e);
		}
	}
	
	public static String getProperties(String key){
		String value = propes.getProperty(key.trim());
		if(value==null){
			return null;
		}
		return value.trim();	
	}
	
	public static String getProperties(String key,String defaltValue){
		String value = propes.getProperty(key.trim());
		if(StringUtils.isBlank(value)){
			value = defaltValue;
		}
		return value.trim();
	}

	public static void load(Properties pro, String fileName) {
		try{
			pro.load(new InputStreamReader(PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName), "UTF-8"));
			logger.info("============",new InputStreamReader(PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName),"UTF-8"));
		}catch(Exception e){
			logger.info("加载异常",e);
		}
	}

}
