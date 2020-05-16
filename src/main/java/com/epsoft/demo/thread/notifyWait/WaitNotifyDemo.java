package com.epsoft.demo.thread.notifyWait;

public class WaitNotifyDemo {

    public static void main(String[] args) throws InterruptedException {
        WaitNotifyDemo obj = new WaitNotifyDemo();
        new Thread1(obj).start();
        Thread.sleep(10);
        new Thread2(obj).start();

    }
}
