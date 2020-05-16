package com.epsoft.demo.thread.produceConsumer;

public class Product {

    private String name;

    private String from;

    private int id =0;

    public Product(String name, String from, int id) {
        this.name = name;
        this.from = from;
        this.id = id;
    }

    public Product(String name, String from) {
        this.name = name;
        this.from = from;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
