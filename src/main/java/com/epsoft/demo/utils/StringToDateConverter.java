package com.epsoft.demo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.DataFormatException;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

/**
 * controller层传对象String转data工具类
 * @author Administrator
 *
 */
public class StringToDateConverter implements Converter<String,Date> {
	
	private static final String dateFormat = "yyyy-MM-dd HH:mm:ss";
	
	private static final String shortFormat ="yyyy-MM-dd";

	@Override
	public Date convert(String source) {
		if(StringUtils.isEmpty(source)){
			return null;
		}
		source = source.trim();
		try{
			if(source.contains("-")){
				SimpleDateFormat simpleDateFormat;
				if(source.contains(":")){
					simpleDateFormat=new SimpleDateFormat(dateFormat);
				}else{
					simpleDateFormat=new SimpleDateFormat(shortFormat);
				}
				Date dtDate = simpleDateFormat.parse(source);
				return dtDate; 
			}else if(source.matches("^\\d+$")){
				 Long lDate = new Long(source);
	             return new Date(lDate);
			}	
		}catch(Exception e){
			throw new RuntimeException(String.format("parse %s to date fail", source));
		}
		throw new RuntimeException(String.format("parse %s to date fail", source));
	}
	
	
}
