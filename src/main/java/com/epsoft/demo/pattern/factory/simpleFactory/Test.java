package com.epsoft.demo.pattern.factory.simpleFactory;

public class Test {

	public static void main(String[] args) {
		//传统实现
		//录制不同的视频要new不同的类依赖不同的video,而且要导入依赖不同的包。
		//于是演变成简单工厂VideoFactory。只需要传入一个参数即可
		Video video = new JavaVideo();
		video.produce();
		
		//现在simpleFactoryTest只依赖一个VideoFactory这就是简单工厂
		VideoFactory videoFactory = new VideoFactory();
		videoFactory.getVideo("python");
		
		//测试通过反射实现的简单工厂 比如Calendar类 mysql类
		videoFactory.getVideoByReflect(JavaVideo.class);
	}
}
