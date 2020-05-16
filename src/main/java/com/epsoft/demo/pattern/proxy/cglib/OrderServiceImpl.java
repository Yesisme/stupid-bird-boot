package com.epsoft.demo.pattern.proxy.cglib;

import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl extends BaseService{

    public String doBusiness(String name){

        return name+":order";
    }


}
