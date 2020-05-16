package com.epsoft.demo.pattern.proxy.cglib;

import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
@Slf4j
public class BaseServiceProxy implements MethodInterceptor {


    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        log.info("o:{},method:{},args:{},methodProxy:{}",o,method,args,methodProxy);
        return methodProxy.invokeSuper(o,args);
    }
}
