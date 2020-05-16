package com.epsoft.demo.utils;

import java.lang.reflect.Field;
import java.util.Properties;

import com.epsoft.demo.annotation.Value;
import com.epsoft.demo.exception.ParamterVaildException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InjectUtil {

	public static void inject(Object obj,Properties properties) {
		Field[] fields = obj.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			//判断是否存在Value注解
			boolean anonationValue = field.isAnnotationPresent(Value.class);
			if(anonationValue) {
				//如果存在,则得到这个注解对象
				Value valueClass = field.getAnnotation(Value.class);
				//获取注解中方法的值
				String valueMethodValue = valueClass.value();
				String inPropertyValue = properties.getProperty(valueMethodValue);
				try {
					//获取属性的类型全称
					String fieldTypeName = field.getGenericType().toString();
					//给属性赋值
					field.set(obj, covert(fieldTypeName,inPropertyValue));
				} catch (IllegalArgumentException e) {
					log.error(e.getMessage());
					throw new ParamterVaildException(field.getName()+"解析异常"+":"+e.getMessage());
				} catch (IllegalAccessException e) {
					log.error(e.getMessage());
					throw new ParamterVaildException(field.getName()+"类中不存在构造方法"+":"+e.getMessage());
				}
				
			}
		}
	}

	private static Object covert(String aClass,String propertiesValue) {
		switch (aClass) {
		case "class java.lang.String":
			return String.valueOf(propertiesValue);
		case "int":
		case "class java.lang.Integer":
			return Integer.valueOf(propertiesValue);
		case "float":
		case "class java.lang.Float":	
			return Float.valueOf(propertiesValue);
		case "Boolean":	
		case "class java.lang.boolean":
			if(propertiesValue.equalsIgnoreCase("true")) {
				return true;
			}
			return false;
		case "long":
		case "class java.lang.Long":
			return Long.valueOf(propertiesValue);
		case "double":
		case "class java.lang.Double":	
			return Double.valueOf(propertiesValue);
		default:
			break;
		}
		return "";	
	}
}
