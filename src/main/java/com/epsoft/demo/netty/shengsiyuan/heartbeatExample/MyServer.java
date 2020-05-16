package com.epsoft.demo.netty.shengsiyuan.heartbeatExample;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
/**
 * 心跳检测
 * @author hp
 *
 */
public class MyServer {

	public static void main(String[] args) throws Exception {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workGroup = new NioEventLoopGroup();
		
		try {
			ServerBootstrap serverBootstrap = new ServerBootstrap();
			serverBootstrap.group(bossGroup, workGroup).channel(NioServerSocketChannel.class).
			handler(new LoggingHandler(LogLevel.INFO)).
			childHandler(new MyServerInitializer());
			
			ChannelFuture channelFuture = serverBootstrap.bind(8898).sync();
			channelFuture.channel().closeFuture().sync();
		}finally {
			bossGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
	}
}
