package com.epsoft.demo.pattern.proxy.staticProxy;


import com.epsoft.demo.pattern.proxy.jdk.RealSubject;
import com.epsoft.demo.pattern.proxy.jdk.SubJect;

public class Proxy implements SubJect {

    private RealSubject realSubject;

    public Proxy(RealSubject realSubject) {
        this.realSubject = realSubject;
    }

    @Override
    public void english() {
       this.realSubject.english();
    }

    @Override
    public void math() {
        this.realSubject.math();
    }
}
