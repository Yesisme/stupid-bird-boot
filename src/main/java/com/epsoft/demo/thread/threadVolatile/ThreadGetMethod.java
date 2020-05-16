package com.epsoft.demo.thread.threadVolatile;

public class ThreadGetMethod implements Runnable {

    @Override
    public void run() {
        ThreadVolitailResort resort = new ThreadVolitailResort();
        resort.method1();
        resort.method2();
        System.out.println(resort.a);
    }
}
