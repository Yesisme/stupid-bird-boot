package com.epsoft.demo.jdk8.copy.v1;

import lombok.Data;

@Data
public class Factory {

    private String name;

    private Job job;

    public Factory(){}

    public Factory(Job job){
        this.job = job;
    }

    public void getJob(Job job) {
        job.doJob(this.name);
    }

    public void totalDay(){
        getJob();
    }

    public static void main(String[] args) {
        String name ="张三";
        new Factory(new Job() {
            @Override
            public void doJob(String str) {
                System.out.println("factory do job");
            }
        });

        Factory f = new Factory();
        f.setName("张三");

        f.getJob((s->{
            System.out.println(s);
        }));


    }
}
