package com.epsoft.demo.jdk8;

import java.util.Map;
import java.util.Properties;

import com.alibaba.fastjson.JSON;
import com.epsoft.demo.utils.PropertiesUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class ListPa{
	
	private static Properties propes = new Properties();
	
	public static void listPageSort(String fileName,Integer currentPage,Integer pageSize) {
		PropertiesUtil.load(propes, fileName);
		System.out.println(propes);
		Map parseObject = JSON.parseObject(propes.get("front.mockMap[ssc.personal.medicalInsuranceRecordList.sort]").toString(), Map.class);
		Map map = JSON.parseObject(parseObject.get("data").toString(), Map.class);
		System.out.println(map);
	}
}
public class LoadProperties {

	public static void main(String[] args) {
		ListPa.listPageSort("mock.properties",4, 12);
	}
}
