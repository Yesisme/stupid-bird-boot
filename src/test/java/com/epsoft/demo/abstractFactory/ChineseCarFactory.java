package com.epsoft.demo.abstractFactory;

public class ChineseCarFactory extends CarFactory{
    @Override
    Glass getGlass() {
        return new ChineseGlass();
    }

    @Override
    Shoe getShoe() {
        return new ChineseShoe();
    }

    @Override
    Engine getEngin() {
        return new ChineseEngin();
    }
}
