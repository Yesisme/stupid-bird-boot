package com.epsoft.demo.thread.threadVolatile;

public class VolatileTest {

    public static void main(String[] args) {
        for (int i = 0; i <10 ; i++) {
            new Thread(new ThreadGetMethod()).start();
        }
    }
}
