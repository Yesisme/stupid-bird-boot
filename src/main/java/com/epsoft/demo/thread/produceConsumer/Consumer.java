package com.epsoft.demo.thread.produceConsumer;

public class Consumer implements Runnable{

    private Storage storage;

    public Consumer(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        try{
            while(true){
                storage.pop();
                Thread.sleep(500);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
