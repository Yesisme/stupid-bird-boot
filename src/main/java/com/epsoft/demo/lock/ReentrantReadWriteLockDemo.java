package com.epsoft.demo.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache {

     private volatile Map<String,Object> map = new HashMap<String,Object>();

     private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

     //写锁是独占锁，写的过程中不能被打断，一定要等到当前线程写入完成。
    public void put(String key, Object value){
        try {
            rwLock.writeLock().lock();
            System.out.println(Thread.currentThread().getName()+"\t 正在写入"+value);
            try { Thread.sleep(300); } catch (InterruptedException e) { e.printStackTrace(); }
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"\t 写入完成"+value);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            rwLock.writeLock().unlock();
        }
    }

    //读锁允许多个线程读
    public void get(String key){
        try{
            rwLock.readLock().lock();
            System.out.println(Thread.currentThread().getName()+"\t 正在读取"+key);
            try { Thread.sleep(300); } catch (InterruptedException e) { e.printStackTrace(); }
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t 读取完成"+result);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            rwLock.readLock().unlock();
        }
    }
}

public class ReentrantReadWriteLockDemo {

    public static void main(String[] args) {

        MyCache cache = new MyCache();
        for (int i = 1; i <= 5; i++) {
            final int temp = i;
            new Thread(()->{
                cache.put(temp+"",temp);
            },String.valueOf(i)).start();
        }

        for (int i = 1; i <= 5; i++) {
            final int temp = i;
            new Thread(()->{
                cache.get(temp+"");
            },String.valueOf(i)).start();
        }
    }
}
