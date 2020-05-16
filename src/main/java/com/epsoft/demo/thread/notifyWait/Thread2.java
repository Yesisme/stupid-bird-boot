package com.epsoft.demo.thread.notifyWait;

public class Thread2 extends Thread {

    private Object obj;
    public Thread2(Object obj){
        this.obj =obj;
    }
    @Override
    public void run() {
        synchronized (obj){
            System.out.println(Thread.currentThread().getName()+"线程2开始了");
            obj.notify();//Thread2唤醒了Thread1，
            System.out.println(Thread.currentThread().getName()+"线程2结束了");
        }

    }
}
