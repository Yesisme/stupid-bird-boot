package com.epsoft.demo.interactive;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

class clientCharTest {
	public static void clientCharTest() throws Exception {
		Socket s = new Socket("127.0.0.1", 23000);
		BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
		Map<String, String> map = new HashMap<>();
		map.put("name", "张三");
		map.put("age", "22");
		map.put("address", "杭州滨江");
		map.put("phone", "324343");
		bw.write(JSON.toJSONString(map), 0, JSON.toJSONString(map).length());
		bw.newLine();
		bw.flush();
		String line = null;
		while ((line = br.readLine()) != null) {
			System.out.println(line);
		}
		s.close();
	}
}

class clientByteTest {
	public static void clientByteTest() throws Exception {
		Socket s = new Socket("127.0.0.1", 10087);

		BufferedOutputStream out = new BufferedOutputStream(s.getOutputStream());
		//OutputStream out = s.getOutputStream();

		Map<String, String> map = new HashMap<>();
		map.put("name", "张三");
		map.put("age", "22");
		map.put("address", "杭州滨江");
		map.put("phone", "324343");
		out.write(JSON.toJSONString(map).getBytes());
		out.flush();
		
		BufferedInputStream in = new BufferedInputStream(s.getInputStream());
		//InputStream in = s.getInputStream();
		byte[] bt = new byte[1024];
		int len = in.read(bt);
		System.out.println("len"+len);
		String content = new String(bt, 0, len);
		System.out.println("content" + content);
		s.close();
	}
}

public class TcpClient {
	public static void main(String[] args) throws Exception {
		//clientCharTest.clientCharTest();
		clientByteTest.clientByteTest();
	}
}
