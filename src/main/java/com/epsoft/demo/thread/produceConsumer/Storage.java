package com.epsoft.demo.thread.produceConsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

//仓库
public class Storage {

    BlockingQueue<Product> queue = new LinkedBlockingQueue<>();

    private int index=1;

    //消费产品
    public void pop() throws InterruptedException {
        Product product = queue.take();
        System.out.println(product.getFrom()+","+product.getName()+","+product.getId()+"被消费了");
    }

    //生成产品
    public void push(Product product) throws InterruptedException {
        queue.put(product);
        product.setId(index++);
        System.out.println(product.getFrom()+",生产了"+ product.getName()+"第"+product.getId()+"手机");
    }
}
