package com.epsoft.demo.thread;

public class ThreadInterrupt {

    static int i;

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(()->{

            while(!Thread.currentThread().isInterrupted()){
                i++;
            }
            System.out.println("线程之外输出"+i);
        },"interrupt");
        t.start();
        //Thread.sleep(1);
        t.interrupt();
    }
}
