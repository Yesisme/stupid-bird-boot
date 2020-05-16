package com.epsoft.demo.thread.threadVolatile;

public class ThreadVolitailResort {

    int a = 0;
    boolean flag = false;

    public void method1() {
        a = 1;
        flag = true;
    }

    public void method2() {
        if (flag = true) {
            a = a + 5;
        }
    }
}
