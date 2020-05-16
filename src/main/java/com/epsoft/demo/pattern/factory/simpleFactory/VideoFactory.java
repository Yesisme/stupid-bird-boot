package com.epsoft.demo.pattern.factory.simpleFactory;

public class VideoFactory {

	//简单工厂弊端，随着录制越多愈多的视频,需要不断扩展,需要写很多的if,
	//不符合开闭原则。应该对扩展开发，对修改关闭。
	public void getVideo(String type) {
		if("java".equalsIgnoreCase(type)) {
			Video video = new JavaVideo();
			video.produce();
		}else if("python".equalsIgnoreCase(type)) {
			Video video = new PythonVideo();
			video.produce();
		}
	}

	//通过反射实现
	public void getVideoByReflect(Class c) {
		try {
			System.out.println(c.getName());
			Video video = (Video) Class.forName(c.getName()).newInstance();
			//Video video = (Video) c.newInstance();
			video.produce();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {		
			e.printStackTrace();
		}
	}
}
