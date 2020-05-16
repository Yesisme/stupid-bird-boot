package com.epsoft.demo.jdk8;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epsoft.demo.utils.DateUtils;


class ListSort{
	
	Logger log = LoggerFactory.getLogger(ListSort.class);
	
	public static void sortList() {
		Map<String,Object> map1 = new HashMap<>();
		map1.put("addTime","2019-08-22 12:12:11");
		map1.put("name", "张三");
		
		Map<String,Object> map2 = new HashMap<>();
		map2.put("addTime", "2019-08-22 11:10:12");
		map2.put("name", "李四");
		
		Map<String,Object> map3 = new HashMap<>();
		map3.put("addTime","2019-09-01 12:12:11");
		map3.put("name", "王五");
		
		List<Map<String, Object>> list = Arrays.asList(map1,map2,map3);
		list.sort((map1Value,map2Value)->{
			try {
				return DateUtils.stringToDate(map2Value.get("addTime").toString(),DateUtils.DATE_TIME_FORMATE).compareTo(DateUtils.stringToDate(map1Value.get("addTime").toString(),DateUtils.DATE_TIME_FORMATE));
			} catch (ParseException e) {
				
			}
			return 1;
		});
		System.out.println(list);
		
	}
	
	public static void ListConvert() {
		List<Map> list1 = new ArrayList<>();
		Map map1 = new HashMap<>();
		map1.put("tradementType", "养老供养");
		map1.put("cardType", "");
		map1.put("bankName", "海宁农村信用社");
		map1.put("cardNum","6230915645327463256");
		
		Map map2 = new HashMap<>();
		map2.put("tradementType", "城乡居民");
		map2.put("cardType", "");
		map2.put("bankName", "海宁农村信用社");
		map2.put("cardNum","6230915645327463256");
		
		Map map3 = new HashMap<>();
		map3.put("tradementType", "土地征保");
		map3.put("cardType", "");
		map3.put("bankName", "海宁农商银行");
		map3.put("cardNum","6324324915645327463256");
		
		list1.add(map1);
		list1.add(map2);
		list1.add(map3);
		List<Object> chongfu = list1.stream().map(mapItemV->mapItemV.get("cardNum")).collect(Collectors.toList());
		System.out.println(chongfu);
		for (Map map : list1) {
			List<Map> oldTradementTypeList = new ArrayList<>();
			Map<String,Object> oldTradementTypeMap = new HashMap<>();
			oldTradementTypeMap.put("tradementType", map.get("tradementType"));
			oldTradementTypeList.add(oldTradementTypeMap);
			map.put("tradementList", oldTradementTypeList);
			map.remove("tradementType");
		}

		long st = System.currentTimeMillis();
		List<String> newList = list1.stream().map(mapVa->mapVa.get("cardNum").toString()).distinct().collect(Collectors.toList());
		List<String> cardNumList = new ArrayList<>();
		List<String> multipleList = new ArrayList<>();
		Stream<Map> maplist = Stream.of(map1,map2,map3);
		for (Map map : list1) {
			String cardNum = (String)map.get("cardNum");
			if(!cardNumList.contains(cardNum)) {
				cardNumList.add(cardNum);
			}else {
				multipleList.add(cardNum);
			}
		}
		System.out.println(multipleList);
		long end = System.currentTimeMillis();
		System.out.println("耗时"+String.valueOf(end-st));
		
		//[6230915645327463256]
		System.out.println(multipleList);
		for(String s : multipleList) {
			List<Map> cardNum = list1.stream().filter(mapItem->mapItem.get("cardNum").equals(s)).collect(Collectors.toList());
			List<Object> collect = cardNum.stream().map(mapValue->mapValue.get("tradementList")).collect(Collectors.toList());
			//[[{tradementType=养老供养}], [{tradementType=城乡居民}]]
			System.out.println(collect);
			list1.removeIf(mapt->mapt.get("cardNum").equals(s));
			Map map11 = cardNum.get(0);
			map11.put("tradementList", collect);
			list1.add(map11);
			//[{cardNum=6324324915645327463256, tradementList=[{tradementType=土地征保}], cardType=, bankName=海宁农商银行}, {cardNum=6230915645327463256, tradementList=[[{tradementType=养老供养}], [{tradementType=城乡居民}]], cardType=, bankName=海宁农村信用社}]
			System.out.println(list1);

		}
		
	}
}

public class ListTest {

	public static void main(String[] args) {
		//ListSort.sortList();
		ListSort.ListConvert();
	}
}
