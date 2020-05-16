package com.epsoft.demo.base64;

public class Test {

	public static void main(String[] args) throws Exception {
		String base642String = Base64PictureUtil.base642String("F:\\33060410392849.jpg");
		System.out.println(base642String);
		Base64PictureUtil.saveFile(base642String,"F:\\time.jpg");
	}
}

