package com.epsoft.demo.util;

import java.util.HashMap;
import java.util.Map;

public class TestUtil {

    public static void main(String[] args) {

        Map<String,String> map = new HashMap<String,String>(10);
        map.put("1000","test001");
        System.out.println(map.put("1000","test002"));
        System.out.println(map);
    }
}
