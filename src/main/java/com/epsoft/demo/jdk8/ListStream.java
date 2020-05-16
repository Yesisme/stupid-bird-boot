package com.epsoft.demo.jdk8;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSONObject;

class StramSort{
	
	public static void getStream() {
		JSONObject json = new JSONObject();
		Map<String,Object> map1 = new HashMap<>();
		map1.put("insuranceType", "养老");
		map1.put("insuranceStatus", "1");
		
		Map<String,Object> map2 = new HashMap<>();
		map2.put("insuranceType", "失业");
		map2.put("insuranceStatus", "1");
		
		Map<String,Object> map3 = new HashMap<>();
		map3.put("insuranceType", "工伤");
		map3.put("insuranceStatus", "0");
		
		Map<String,Object> map4 = new HashMap<>();
		map4.put("insuranceType", "医疗");
		map4.put("insuranceStatus", "1");
		
		List<Map<String, Object>> insuranceList = Arrays.asList(map1,map2,map3,map4);
		json.put("insuranceList", insuranceList);
		json.put("name", "张三");
		json.put("idNo","36060319981011145x");
		List<Map<String, Object>> collectlist = insuranceList.stream().filter(insuranceTypeMap->insuranceTypeMap.get("insuranceStatus").toString().equals("1")).collect(Collectors.toList());
		collectlist.stream().map(insurancemap->convertType(insurancemap)).collect(Collectors.toList());
		collectlist.sort((map1Item,map2Item)->Integer.valueOf(map1Item.get("insuranceType").toString()).compareTo(Integer.valueOf(map2Item.get("insuranceType").toString())));
		
		json.put("insuranceList", collectlist);
		
		System.out.println(json);
	}
	
	private static Map<String,Object> convertType(Map<String,Object> insuranceTypeMap) {
		String type = (String)insuranceTypeMap.get("insuranceType");
		String changeType=null;   
		switch (type) {
		case "养老":
			changeType="1";
			break;
		case "医疗":
			changeType="2";
			break;
		case "工伤":
			changeType="3";
			break;
		case "失业":
			changeType="4";
			break;
		case "生育":
			changeType="5";
			break;
		default:
			changeType="--";
			break;
		}
		insuranceTypeMap.put("insuranceType", changeType);
		return insuranceTypeMap;
	}
}
public class ListStream {

	public static void main(String[] args) {
		StramSort.getStream();
	}
}
