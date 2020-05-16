package com.epsoft.demo.service.user.impl;
import org.springframework.context.annotation.Bean;

import com.epsoft.demo.service.user.SessionService;

import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Session服务层实现类
 * 通过动态代理实现
 * @author Cheese
 */
public class SessionServiceImpl implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        String methodName = method.getName();
        /* 读操作 */
        if (methodName.startsWith("get"))
            return ((HttpSession) args[0]).getAttribute(methodName.substring(3));
        /* 写操作 */
        else if (methodName.startsWith("set")) {
            ((HttpSession) args[0]).setAttribute(methodName.substring(3), args[1]);
            return null;
        } else 
            throw new RuntimeException("方法名错误");
    }

    /**
     * 动态代理RedisService
     */
    @Bean
    public SessionService redisService() {
        return (SessionService) Proxy.newProxyInstance(SessionService.class.getClassLoader(), new Class[]{SessionService.class}, this);
    }
}
