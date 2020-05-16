package com.epsoft.demo.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;


class input{
	public static void testInputStream() throws Exception{
		BufferedInputStream in = new BufferedInputStream(new FileInputStream("F:\\INFO.txt"));
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("F:\\INFO1.txt"));
		byte[] by = new byte[1024];
		Map<String,String> map = new HashMap<>();
		map.put("name","zhangsan");
		map.put("address","zhangsan");
		out.write(JSON.toJSONString(map).getBytes());
		int len = 0;
		while((len=in.read(by))!=-1) {
			out.write(by, 0, len);
		}
		in.close();
		out.close();
	}
}

class write{
	public static void testWriter() throws Exception, FileNotFoundException {
		InputStreamReader inRead = new InputStreamReader(new FileInputStream("F:\\io.txt"),"utf-8");
		OutputStreamWriter inWriter = new OutputStreamWriter(new FileOutputStream("F:\\INFO.txt"),"utf-8");
		char[] ch = new char[1024];
		int len=0;
		while((len =inRead.read(ch))!=-1) {
			inWriter.write(ch, 0, len);
		}
		inRead.close();
		inWriter.close();
	}
}

class read{
	public static void testReadStream() throws IOException {
		BufferedReader bufRead = new BufferedReader(new FileReader("F:\\io.txt"));
		BufferedWriter bufWriter = new BufferedWriter(new FileWriter("F:\\INFO.txt"));
		String line = null;
		while ((line=bufRead.readLine())!=null) {
			bufWriter.write(line);
			bufWriter.newLine();
			bufWriter.flush();
		}
		bufRead.close();
		bufWriter.close();
	}
}


class udp{
	public static void testUdp() throws Exception {
		InetAddress address = InetAddress.getLocalHost();
		String hostAddress = address.getHostAddress();
		System.out.println("ip:"+hostAddress);
		String hostName = address.getHostName();
		System.out.println("hostName:"+hostName);	
	}

}

public class File {

	public static void main(String[] args) throws Exception {
		//input.testInputStream();
		//read.testReadStream();
		write.testWriter();
	}
}
