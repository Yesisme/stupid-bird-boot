package com.epsoft.demo.interactive;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpClient  {
	public static void main(String[] args) throws Exception {
		// 创建接收端的Socket对象
		DatagramSocket ds = new DatagramSocket(12345);

		// 创建一个包裹
		while(true) {
		byte[] buf = new byte[1024];
		DatagramPacket dp = new DatagramPacket(buf, buf.length);
		// 接收数据
		ds.receive(dp);
		// 解析数据
		String hostAddress = dp.getAddress().getHostAddress();
		
		String s = new String(dp.getData(),0,dp.getLength());
		System.out.println("ip是："+hostAddress+"data is"+s);
		
		// 释放资源
		// 接收端应该一直开着等待接收数据，是不需要关闭
		ds.close();
		}
	}
}
