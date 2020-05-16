package com.epsoft.demo.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletRequest;

import org.springframework.stereotype.Component;	
	/**
	 * 预反射器
	 */
	@Component
	public class ReflectUtil {
		
	public static final DateFormat DF = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	
	private static class Tuple {
	public final Converter converter;
	public final Field field;
	
	public Tuple(Converter converter, Field field) {
	this.converter = converter;
	this.field = field;
	}
	}
	private Map<Class, Tuple[]> reflectMap;
	public ReflectUtil () {
	/* TODO 需要反射的Class 可以写死或者通过注解扫描得到 */
	Set<Class> classSet=new HashSet();
	reflectMap = new HashMap<>();
	for (Class clazz : classSet) {
	Field[] fields = clazz.getDeclaredFields();
	Tuple[] tuples = new Tuple[fields.length];
	for (int i=0; i<fields.length; i++) {
	fields[i].setAccessible(true);
	Class fieldType = fields[i].getType();
	if (fieldType == String.class)
	tuples[i] = new Tuple(Converter.STRING, fields[i]);
	else if (fieldType == Integer.class)
	tuples[i] = new Tuple(Converter.INTEGER, fields[i]);
	else if (fieldType == Double.class)
	tuples[i] = new Tuple(Converter.DOUBLE, fields[i]);
	else if (fieldType == Boolean.class)
	tuples[i] = new Tuple(Converter.BOOLEAN, fields[i]);
	else if (fieldType == String[].class)
	tuples[i] = new Tuple(Converter.STRINGS, fields[i]);
	else if (fieldType == Date.class)
	tuples[i] = new Tuple(Converter.DATE, fields[i]);
	// TODO 省略其他类型判断
	else
	throw new RuntimeException("发现未知属性类型，请配置到Converter");
	}
	reflectMap.put(clazz, tuples);
	}
	}
	public <T> T convert(Class<T> clazz, ServletRequest request) throws Exception {
	T object = clazz.getConstructor().newInstance();
	Tuple[] tuples = reflectMap.get(clazz);
	for (Tuple tuple : tuples) 
	tuple.converter.convert(object, tuple.field, request);
	return object;
	}
	}
