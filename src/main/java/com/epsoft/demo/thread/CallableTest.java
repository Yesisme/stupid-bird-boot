package com.epsoft.demo.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class myThread implements Callable{

    @Override
    public Object call() throws Exception {
        System.out.println(Thread.currentThread().getName()+"\t"+"#######common in##########");
        return "2020";
    }
}
public class CallableTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask futureTask = new FutureTask(new myThread());

        Thread t1 = new Thread(futureTask,"A");
        t1.start();

        int result =10;
        System.out.println(Integer.valueOf(futureTask.get().toString())+result);
        System.out.println("cpu核数：\t"+Runtime.getRuntime().availableProcessors());

        futureTask.get();
    }

}
