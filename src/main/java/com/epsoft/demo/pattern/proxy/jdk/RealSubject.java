package com.epsoft.demo.pattern.proxy.jdk;

public class RealSubject implements SubJect {

    @Override
    public void english() {
        System.out.println("开始读英语");
    }

    @Override
    public void math() {
        System.out.println("开始读数学");
    }
}
