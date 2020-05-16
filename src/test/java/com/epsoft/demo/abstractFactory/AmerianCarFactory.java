package com.epsoft.demo.abstractFactory;

public class AmerianCarFactory extends CarFactory{
    @Override
    Glass getGlass() {
        return new AmerianGlass();
    }

    @Override
    Shoe getShoe() {
        return new AmerianShoe();
    }

    @Override
    Engine getEngin() {
        return new AmerianEngine();
    }
}
