package com.epsoft.demo.rpc.one;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * 弊端，序列化局限性，bio效率低，tcp连接池频繁创建销毁（apache pool2），服务负责自动发现（zookeeper），
 * @author Administrator
 *
 * @param <T>
 */
public class RpcClientProxy<T> implements InvocationHandler {
	
	private Class<T> serviceInterface;
	
	private InetSocketAddress addr;
	
	public RpcClientProxy(Class<T> serviceInterface,String ip,int port){
		this.serviceInterface=serviceInterface;
		this.addr = new InetSocketAddress(ip, port);
	}
	
	public T getProxyInstance(){
		return (T)Proxy.newProxyInstance(serviceInterface.getClassLoader(), new Class<?>[]{serviceInterface}, this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Socket socket = null;
		ObjectInputStream in = null;
		ObjectOutputStream out = null;
		
		try {
			socket = new Socket();
			socket.connect(this.addr);
			
		// 3.将远程服务调用所需的接口类、方法名、参数列表等编码后发送给服务提供者
			out = new ObjectOutputStream(socket.getOutputStream());
			out.writeUTF(serviceInterface.getName());
			out.writeUTF(method.getName());
			out.writeObject(method.getParameterTypes());
			out.writeObject(args);
			
		// 4.同步阻塞等待服务器返回应答，获取应答后返回	
			in = new ObjectInputStream(socket.getInputStream());
			return in.readObject();
		}finally {
			 if (socket != null) socket.close();
	         if (out != null) out.close();
	         if (in != null) in.close();
		}
	}
	
	public static void main(String[] args) {
		RpcClientProxy rpc = new RpcClientProxy<>(IService.class, "127.0.0.1", 6666);
		IService cs = (IService) rpc.getProxyInstance();
		System.out.println(cs.say("socket rpc"));
	}
}
