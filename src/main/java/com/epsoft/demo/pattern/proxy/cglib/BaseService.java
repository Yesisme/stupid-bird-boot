package com.epsoft.demo.pattern.proxy.cglib;

public class BaseService {

    public String doBusiness(String name){
        return name;
    }

    public final String hand(){
        return "我是final";
    }
}