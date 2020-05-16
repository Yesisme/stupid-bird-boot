package com.epsoft.demo.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.Map.Entry;

import org.junit.Test;

public class MockTest {
	
	Properties properties = new Properties();

	@Test
	public void getAllProperties() {
		
		InputStream in = MockTest.class.getClassLoader().getResourceAsStream("mock.properties");
		try {
			properties.load(in);
			Map map = new HashMap<>();
			
			Set<Entry<Object, Object>> entrySet = properties.entrySet();
			for (Entry<Object, Object> entry : entrySet) {
				map.put(entry.getKey(), entry.getValue());
			}
			System.out.println(map.keySet().size());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(this.properties.keySet().size());
	}

	@Test
	public void testFor(){
		List<String> list = new ArrayList<>(Arrays.asList("191323432","19234857","193543545"));
		for(int i=0;i<list.size();i++){
			String s = list.get(i);
			if(!s.startsWith("193")){
				list.remove(i);
				i--;
			}
		}
		list.forEach(s->System.out.println(s));
	}
}
