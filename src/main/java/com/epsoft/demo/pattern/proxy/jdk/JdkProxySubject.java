package com.epsoft.demo.pattern.proxy.jdk;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
@Slf4j
public class JdkProxySubject implements InvocationHandler {

    private SubJect subject;

    public JdkProxySubject(SubJect subject){
        this.subject =subject;
    }
    //
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        log.info("###########{}","before");
        Object result = null;
        try {
            result = method.invoke(this.subject,args);
        }catch (Exception e){
            log.info(e.getMessage(),e);
        }finally {
            log.info("###########{}","after");
        }
        return result;
    }
}
