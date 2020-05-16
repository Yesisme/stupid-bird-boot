package com.epsoft.demo.interactive;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

class serverCharTest {
	public static void serverCharTest() throws Exception {
		ServerSocket s = new ServerSocket(23000);
		Socket socket = s.accept();
		System.out.println("socket...." + socket);
		BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		System.out.println(br);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		System.out.println(bw);
		Map<String, String> map = new HashMap<>();
		map.put("idNo", "3308383738748");
		bw.write(JSON.toJSONString(map));
		bw.newLine();
		bw.flush();
		System.out.println(bw);
		String line = null;
		while ((line = br.readLine()) != null) {
			System.out.println(line);
		}
		socket.close();
	}
}

class serverByteTest {
	public static void serverByteTest() throws Exception {
		ServerSocket socket = new ServerSocket(10087);
		Socket s = socket.accept();// 阻塞
		
		BufferedOutputStream out = new BufferedOutputStream(s.getOutputStream());
		//OutputStream out = s.getOutputStream();
		Map<String, String> map = new HashMap<>();
		map.put("idNo", "3308383738748");
		out.write(JSON.toJSONString(map).getBytes("gbk"));
		out.flush();
		
		BufferedInputStream in = new BufferedInputStream(s.getInputStream());
		//InputStream in = s.getInputStream();
		byte[] bt = new byte[1024];
		/*
		 * int len = in.read(bt);// 阻塞 System.out.println("len"+len);
		 */
		int len =0;
		while((len=in.read(bt))!=-1) {
			String content = new String(bt, 0, len);
			System.out.println("content" + content.getBytes("gbk"));
		}
		s.close();
	}
}

public class TcpServer {
	public static void main(String[] args) throws Exception {
		//serverCharTest.serverCharTest();
		serverByteTest.serverByteTest();
}
}