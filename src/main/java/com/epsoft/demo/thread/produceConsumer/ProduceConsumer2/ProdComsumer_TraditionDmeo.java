package com.epsoft.demo.thread.produceConsumer.ProduceConsumer2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData {

    private int number = 0;

    private Lock lock = new ReentrantLock();

    private Condition  fullCondition = lock.newCondition();
    private Condition  emptyCondition = lock.newCondition();

    public void increment() {
        lock.lock();
        try {
            while (number == 10) {
                fullCondition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            emptyCondition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public void deCrement() {
        lock.lock();

        try {
            while (number == 0) {
                emptyCondition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            fullCondition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}


public class ProdComsumer_TraditionDmeo {

    public static void main(String[] args) {
        ShareData shareData = new ShareData();

        for (int i = 0; i <2 ; i++) {
        new Thread(()->{
            shareData.increment();
        }).start();
        }
        for (int i = 0; i <5 ; i++) {
        new Thread(()->{
                shareData.deCrement();
        }).start();
        }
    }
}
