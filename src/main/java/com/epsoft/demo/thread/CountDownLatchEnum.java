package com.epsoft.demo.thread;

import io.swagger.models.auth.In;
import lombok.Getter;

public enum CountDownLatchEnum {

    ONE(1,"楚国"),TWO(2,"赵国"),THREE(3,"魏国"),FOUR(4,"齐国"),FIVE(5,"韩国"),SEX(6,"晋国");


    @Getter private int temp;

    @Getter private String name;

    CountDownLatchEnum(int temp, String name) {
        this.temp = temp;
        this.name = name;
    }

    protected static String getName(int i){
        CountDownLatchEnum[] myArray = CountDownLatchEnum.values();
        for (CountDownLatchEnum element:myArray) {
            if(i==element.getTemp()){
                return element.getName();
            }
        }
        return null;
    }

}
