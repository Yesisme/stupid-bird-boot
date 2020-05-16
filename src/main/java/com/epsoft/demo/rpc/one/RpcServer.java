package com.epsoft.demo.rpc.one;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class RpcServer {
	
	private static final HashMap<String,Class<?>> serviceRegistry = new HashMap<>();
	
	private int port;
	
	public RpcServer(int port) {
		this.port = port;
	}
	
	public RpcServer registry(Class serviceInterface,Class impl) {
		this.serviceRegistry.put(serviceInterface.getName(), impl);
		return this;
	}
	
	public void run() throws IOException {
		ServerSocket serverSocket = new ServerSocket();
		serverSocket.bind(new InetSocketAddress(port));
		System.out.println("start server");
		ObjectInputStream in = null;
		ObjectOutputStream out = null;
		Socket socket = null;
		try {
			while(true) {
				socket = serverSocket.accept();
				in = new ObjectInputStream(socket.getInputStream());
				String serviceName = in.readUTF();
				String methodName = in.readUTF();
				System.out.println(methodName);
				Class<?>[] paramterType =  (Class<?>[]) in.readObject();
				Object[] argument = (Object[]) in.readObject();
				Class serviceClass = serviceRegistry.get(serviceName);
				if(serviceClass==null) {
					throw new RuntimeException(serviceName+"cannot found");
				}
				Method method = serviceClass.getMethod(methodName, paramterType);
				Object result = method.invoke(serviceClass.newInstance(), argument);
				out = new ObjectOutputStream(socket.getOutputStream());
				out.writeObject(result);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			 if (out != null) {
	                try {
	                    out.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	            if (in != null) {
	                try {
	                    in.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	            if (socket != null) {
	                try {
	                    socket.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
		}
	
		public static void main(String[] args) throws IOException {
			new RpcServer(6666).registry(IService.class, ChinaService.class).run();
		}
}
