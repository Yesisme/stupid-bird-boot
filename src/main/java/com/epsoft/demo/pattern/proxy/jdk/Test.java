package com.epsoft.demo.pattern.proxy.jdk;

import java.lang.reflect.Proxy;

public class Test {

    public static void main(String[] args) {
        SubJect realSubject = (SubJect) Proxy.newProxyInstance(Test.class.getClassLoader(), new Class[]{SubJect.class}, new JdkProxySubject(new RealSubject()));
        realSubject.english();
        realSubject.math();
    }
}
