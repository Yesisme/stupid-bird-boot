package com.epsoft.demo.thread;

import org.apache.poi.ss.formula.functions.T;
import org.omg.PortableServer.THREAD_POLICY_ID;

import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class HeimaThreadPool {


    static class MyTask implements Runnable{

        private int taskId;

        private String taskName;

        public MyTask(int taskId, String taskName) {
            this.taskId = taskId;
            this.taskName = taskName;
        }

        public int getTaskId() {
            return taskId;
        }

        public void setTaskId(int taskId) {
            this.taskId = taskId;
        }

        public String getTaskName() {
            return taskName;
        }

        public void setTaskName(String taskName) {
            this.taskName = taskName;
        }

        @Override
        public void run() {
            System.out.println("@taskId:"+this.taskId+"---->"+"ThreadID:"+Thread.currentThread().getId());
            try{
                Thread.sleep(5000);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        @Override
        public String toString() {
            return Integer.toString(this.taskId);
        }
    }



    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2,
                3,
                30,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3),
                new ThreadPoolExecutor.AbortPolicy());
        //
        for (int i=0;i<=6;i++){
            executor.execute(new Thread(new MyTask(i,"任务"+i)));
        }

        executor.shutdown();
    }
}
