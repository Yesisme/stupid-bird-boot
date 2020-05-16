package com.epsoft.demo.pattern.singleton.container;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ContainerSingleton {

    private static final Map<String,Object> map = new HashMap<>();

    private ContainerSingleton(){}

    public static void  putInstance(String key,Object Instance){
        if(!map.containsKey(key)){
            map.put(key, Instance);
        }
    }

    public static Object getInstance(String key){
        return map.get(key);
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
       new Thread(()->{
            ContainerSingleton.putInstance("1","2");
        },"A").start();

        new Thread(()->{
            ContainerSingleton.putInstance("1","2");
        },"B").start();


        ContainerSingleton containerSingleton1 = ContainerSingleton.class.newInstance();
        containerSingleton1.putInstance("1","2");

        System.out.println();

        ContainerSingleton containerSingleton2 = ContainerSingleton.class.newInstance();
        containerSingleton2.putInstance("1","2");

        System.out.println(containerSingleton1.getInstance("1")==containerSingleton2.getInstance("1"));
    }
}
