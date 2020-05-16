package com.epsoft.demo.thread;

public class MyThread extends Thread {


    @Override
    public void run(){
        System.out.println(Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            MyThread myThread = new MyThread();
            myThread.run();
            myThread.start();
        }

    }

}
