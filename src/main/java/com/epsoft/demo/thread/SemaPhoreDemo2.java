package com.epsoft.demo.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Semaphore;

public class SemaPhoreDemo2 {

    public static void main(String[] args) {


        Semaphore semaphore = new Semaphore(3);
        for (int i = 1; i <=6; i++) {
            final int tempInt = i;
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println("第"+tempInt+"车抢到车位");
                    Thread.sleep(3000);
                    System.out.println("第"+tempInt+"开走了");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }
    }
}
