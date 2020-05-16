package com.epsoft.demo.pattern.proxy.staticProxy;

import com.epsoft.demo.pattern.proxy.jdk.RealSubject;
import com.epsoft.demo.pattern.proxy.jdk.SubJect;
//静态代理
public class Test {

    public static void main(String[] args) {
        SubJect subJect = new Proxy(new RealSubject());
        subJect.english();
        subJect.math();
    }
}
