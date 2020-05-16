package com.epsoft.demo.thread.produceConsumer;

public class Producer implements Runnable{

    //仓库
    private Storage storage;

    //生产者名称
    private String produceName;

    //生产消费品
    private String name;

    public Producer(Storage storage, String produceName, String name) {
        this.storage = storage;
        this.produceName = produceName;
        this.name = name;
    }

    @Override
    public void run() {

        try{
            for (int i=0;i<15;i++){
                Product product = new Product(name,produceName);
                storage.push(product);
            }
        }catch (Exception e){

        }
    }
}
