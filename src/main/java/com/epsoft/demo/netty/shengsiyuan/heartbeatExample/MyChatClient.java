package com.epsoft.demo.netty.shengsiyuan.heartbeatExample;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class MyChatClient {

	public static void main(String[] args) throws Exception {
		EventLoopGroup enEventLoopGroup = new NioEventLoopGroup();
		
		try {
			Bootstrap bootStrap = new Bootstrap();
			bootStrap.group(enEventLoopGroup).channel(NioSocketChannel.class).handler(new MyChatClientInitalizer());
			Channel channel = bootStrap.connect("localhost", 8898).sync().channel();
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			
			for(;;) {
				channel.writeAndFlush(reader.readLine()+"\r\n");
			}
		}finally {
			enEventLoopGroup.shutdownGracefully();
		}
	}
}
