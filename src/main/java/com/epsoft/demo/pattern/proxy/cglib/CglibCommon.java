package com.epsoft.demo.pattern.proxy.cglib;


import com.alibaba.fastjson.JSONObject;
import com.epsoft.demo.utils.SpringUtil;
import net.sf.cglib.proxy.Enhancer;
import org.springframework.stereotype.Component;

@Component
public class CglibCommon {

    public void Common(JSONObject reqJson){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SpringUtil.getBean(reqJson.get("serviceName").toString()).getClass());
        enhancer.setCallback(new BaseServiceProxy());
        BaseService baseService = (BaseService) enhancer.create();
        baseService.hand();
        System.out.println(baseService.doBusiness("serivce name is"));
    }

}
