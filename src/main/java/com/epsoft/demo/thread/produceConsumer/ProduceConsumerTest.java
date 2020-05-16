package com.epsoft.demo.thread.produceConsumer;


import java.util.concurrent.*;

public class ProduceConsumerTest {

    public static void main(String[] args) {
        Storage storage = new Storage();
        Producer p1= new Producer(storage,"小米","红米3");
        Producer p2 = new Producer(storage,"华为","nova4");
        Consumer con = new Consumer(storage);
        //ExecutorService threadpool = Executors.newCachedThreadPool();
        //ExecutorService threadPool = Executors.newFixedThreadPool(10);
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(3,
                4,
                10,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(5),
                new ThreadPoolExecutor.AbortPolicy());
        threadPool.submit(p1);
        threadPool.submit(p2);
        threadPool.submit(con);
    }
}
