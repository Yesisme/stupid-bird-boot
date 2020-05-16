package com.epsoft.demo.utils;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletRequest;

import org.springframework.stereotype.Component;

/**
	 * 参数转换器
	 */
public enum Converter {
	
	STRING {
	public void convert(Object object, Field field, ServletRequest request) {
	try {
		field.set(object, request.getParameter(field.getName()));
	} catch (IllegalArgumentException | IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}}
	},
	
	
	INTEGER {
	public void convert(Object object, Field field, ServletRequest request) {
	try {
		field.set(object, Integer.parseInt(request.getParameter(field.getName())));
	} catch (IllegalArgumentException | IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}}
	},
	
	DOUBLE {
	public void convert(Object object, Field field, ServletRequest request) {
	try {
		field.set(object, Double.parseDouble(request.getParameter(field.getName())));
	} catch (IllegalArgumentException | IllegalAccessException e) {
		e.printStackTrace();
	}}
	},
	
	BOOLEAN {
	public void convert(Object object, Field field, ServletRequest request) {
	try {
		field.set(object, Boolean.parseBoolean(request.getParameter(field.getName())));
	} catch (IllegalArgumentException | IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}}
	},
	
	STRINGS {
	public void convert(Object object, Field field, ServletRequest request) {
	try {
		field.set(object, request.getParameterValues(field.getName()));
	} catch (IllegalArgumentException | IllegalAccessException e) {
		e.printStackTrace();
	}}
	},
	
	DATE {
	public void convert(Object object, Field field, ServletRequest request) {
	try {
		field.set(object, ReflectUtil.DF.parse(request.getParameter(field.getName())));
	} catch (IllegalArgumentException | IllegalAccessException | ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}}
	};


	// TODO 省略其他类型转换器
	public abstract void convert(Object object, Field field, ServletRequest request);
	}
	

