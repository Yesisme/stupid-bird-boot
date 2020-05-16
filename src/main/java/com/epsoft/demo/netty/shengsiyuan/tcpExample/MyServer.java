package com.epsoft.demo.netty.shengsiyuan.tcpExample;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
/**
 * tcp服务端
 * @author hp
 *
 */
public class MyServer {

	public static void main(String[] args) throws Exception {
		EventLoopGroup boosGroup = new NioEventLoopGroup();
		EventLoopGroup workGroup = new NioEventLoopGroup();
		
		try {
			ServerBootstrap serverBootstrap = new ServerBootstrap();
			serverBootstrap.group(boosGroup, workGroup).channel(NioServerSocketChannel.class).childHandler(new MyServerInitializer());
			
			ChannelFuture chanleFuture = serverBootstrap.bind(8899).sync();
			chanleFuture.channel().closeFuture().sync();
		}finally {
			boosGroup.shutdownGracefully();
			workGroup.shutdownGracefully();
		}
	}
}
