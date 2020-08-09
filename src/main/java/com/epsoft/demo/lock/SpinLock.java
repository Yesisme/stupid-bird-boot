package com.epsoft.demo.lock;

import java.util.concurrent.atomic.AtomicReference;
//自旋锁
public class SpinLock {

    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void myLock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+"\t come in ");
        while(!atomicReference.compareAndSet(null,thread)) {}
    }

    public void myUnlock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+"\t come out ");
        atomicReference.compareAndSet(thread,null);
    }

    public static void main(String[] args) {
        SpinLock lock = new SpinLock();

        new Thread(()->{
            lock.myLock();
            try { Thread.sleep(5000); } catch (InterruptedException e) { e.printStackTrace(); }
            lock.myUnlock();
        },"AA").start();

        try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }

        new Thread(()->{
            lock.myLock();
            lock.myUnlock();
        },"BB").start();
    }
}
