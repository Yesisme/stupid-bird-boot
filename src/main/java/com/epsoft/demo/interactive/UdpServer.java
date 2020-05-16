package com.epsoft.demo.interactive;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpServer {

	public static void main(String[] args) throws Exception {
		// 创建发送端的Socket对象
		DatagramSocket ds = new DatagramSocket();
		// 封装键盘录入数据
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		while((line = br.readLine())!=null) {
			if("886".equals(line)) {
				break;
			}
		// 创建数据并打包
			byte[] bys = line.getBytes();
			DatagramPacket dp = new DatagramPacket(bys,bys.length,InetAddress.getByName("192.168.0.130"),12345);
			// 发送数据	
			ds.send(dp);
			
		}
		ds.close();
		// 释放资源	
	}		
}
