package com.epsoft.demo.pattern.singleton.hungry;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

@Slf4j
public class Test {

	public static void main(String[] args) throws Exception {

		// 饿汉式
		HungrySingleton instance = HungrySingleton.getInstance();
		log.info("线程ID是:{}", Thread.currentThread().getId() +"\t"+ instance.hashCode());

		// 序列化破环单例模式,序列化到文件中再取出
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("singleton_file.txt"));
		oos.writeObject(instance);
		
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("singleton_file.txt")));
		HungrySingleton newInstance = (HungrySingleton) ois.readObject();
		log.info("instance是:{}", instance.hashCode());
		log.info("newInstance是:{}", newInstance.hashCode());
		log.info("equals?:{}", instance == newInstance);


		Thread.sleep(2000);
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("==============解决序列化破坏单例==============");
		// 序列化破坏单例解决方案加上readerObject方法
		// 饿汉式
		HungrySingletonReadResolve instance1 = HungrySingletonReadResolve.getInstance();
		log.info("线程ID:{}", Thread.currentThread().getId()  +"\t"+ instance1);

		// 序列化破环单例模式,序列化到文件中再取出
		ObjectOutputStream oos1 = new ObjectOutputStream(new FileOutputStream("F:\\singleton_file.txt"));
		oos1.writeObject(instance1);

		ObjectInputStream ois1 = new ObjectInputStream(new FileInputStream(new File("F:\\singleton_file.txt")));
		HungrySingletonReadResolve newInstance1 = (HungrySingletonReadResolve) ois1.readObject();
		log.info("instance是:{}", instance1.hashCode());
		log.info("newInstance是:{}", newInstance1.hashCode());
		log.info("equals?:{}", instance1 == newInstance1);

	}
}
