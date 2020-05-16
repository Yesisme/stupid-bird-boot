package com.epsoft.demo.factory;

import com.sun.org.apache.regexp.internal.RE;

public class GermanyDefaultAbstractFactory extends CarFactory{

    @Override
    Car getCar() {
        return new GermanyCar();
    }
}
