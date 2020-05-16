package com.epsoft.demo.collections.list;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class ArrayList {

    public static void main(String[] args) throws InterruptedException {

        //List<String> list = Collections.synchronizedList(new java.util.ArrayList<>());
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }

        ReentrantLock lock = new ReentrantLock();
    }
}
