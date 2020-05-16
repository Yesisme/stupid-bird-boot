package com.epsoft.demo.reflect;

import java.lang.reflect.Field;

import org.apache.commons.lang3.StringUtils;

public class HnCommonInBean {

	public String toHnString() {
		try {
			Field[] fields = this.getClass().getDeclaredFields();
			StringBuilder sb = new StringBuilder();
			for(int i = 0;i < fields.length; i++){
				if(i !=0){
					sb.append("&");
				}
				fields[i].setAccessible(true);
				sb.append(fields[i].getName());
				sb.append("=");
				if(fields[i].get(this) instanceof String) {
					if(fields[i].get(this) == null || StringUtils.isBlank((String)fields[i].get(this))) {
						sb.append("-");
					}else {
						sb.append(String.valueOf(fields[i].get(this))==null?"-":String.valueOf(fields[i].get(this)));
					}
				}else {
					sb.append(String.valueOf(fields[i].get(this)));
				}	
			}
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
		
	}
}
