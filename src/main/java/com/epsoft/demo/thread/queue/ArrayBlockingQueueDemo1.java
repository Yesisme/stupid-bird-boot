package com.epsoft.demo.thread.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class ArrayBlockingQueueDemo1 {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue blockingQueue = new ArrayBlockingQueue(4);
        System.out.println(blockingQueue.add("1"));
        System.out.println(blockingQueue.add("2"));
        System.out.println(blockingQueue.add("3"));

        System.out.println(blockingQueue.poll(2L,TimeUnit.SECONDS));

        System.out.println(blockingQueue.offer("4", 2, TimeUnit.SECONDS));
        System.out.println(blockingQueue);
    }
}
