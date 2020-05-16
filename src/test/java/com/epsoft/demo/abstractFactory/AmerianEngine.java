package com.epsoft.demo.abstractFactory;

public class AmerianEngine extends Engine {
    @Override
    void produce() {
        System.out.println("生产美国汽车的引擎");
    }
}
