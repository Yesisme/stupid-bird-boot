package com.epsoft.demo.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.epsoft.demo.bean.entity.YlErrorEntity;

import edu.emory.mathcs.backport.java.util.Arrays;

public class AsciiUtil {

	public static String AsciiSortForStr(String str) {
		char[] checkArr = new char[str.length()];
		StringBuilder sb = new StringBuilder();
		while(true) {
			String checkedArray = str; 
			for(int i=0;i<str.length();i++) {
				checkArr[i] = str.charAt(i);
			}
			Arrays.sort(checkArr);
			for(int j=0;j<checkArr.length;j++) {
				sb.append(checkArr[j]);
			}
			String trim = sb.toString().trim();
			return trim;
		}
	}
	
	public static void AsciiSortForMap(Map<String,Object> map) {
		Set<String> keySet = map.keySet();
		List<String> list= new ArrayList<>();
		for (String str : keySet) {
			list.add(str);
		}
		list.sort((s1,s2)->s1.compareTo(s2));
		list.stream().forEach(s->System.out.println(s));
		int size = list.size();
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<size;i++) {
			sb.append(list.get(i)).append("=").append(map.get(list.get(i))).append("&");
		}
		System.out.println(sb.toString());
	}
	
	public static void main(String[] args) {
		/*YlErrorEntity le = new YlErrorEntity();
		le.setPlatCode("20000");
		le.setPlatMsg("服务不可用");
		le.setSignType("SHA256");
		le.setTraceId("2019022514fd5249a1034c49dca71db4e6aeed1de5");
		Map parseObject = JSON.parseObject(JSON.toJSONString(le), Map.class);
		System.out.println(parseObject);
		Set keySet = parseObject.keySet();
		StringBuilder sb = new StringBuilder();
		for (Object object : keySet) {
			String lowerFirstChar = NumberUtil.lowerFirstChar(object.toString());
			System.out.println(AsciiUtil.AsciiSortForStr(lowerFirstChar));
		}*/
		
		YlErrorEntity le = new YlErrorEntity();
		le.setPlatCode("20000");
		le.setPlatMsg("服务不可用");
		le.setSignType("SHA256");
		le.setTraceId("2019022514fd5249a1034c49dca71db4e6aeed1de5");
		Map map = JSON.parseObject(JSON.toJSONString(le), Map.class);
		AsciiUtil.AsciiSortForMap(map);
	}
}
