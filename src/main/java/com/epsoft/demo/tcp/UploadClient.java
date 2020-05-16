package com.epsoft.demo.tcp;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class UploadClient {
	public static void main(String[] args) throws IOException {
		Socket s = new Socket("127.0.0.1", 11111);
		BufferedReader br = new BufferedReader(new FileReader(
				"ReceiveDemo.java"));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
				s.getOutputStream()));
		String line = null;
		while ((line = br.readLine()) != null) { 
			bw.write(line);
			bw.newLine();
			bw.flush();
		}
		s.shutdownOutput();
		BufferedReader brClient = new BufferedReader(new InputStreamReader(
				s.getInputStream()));
		String client = brClient.readLine(); 
		System.out.println(client);
		br.close();
		s.close();
	}
}
