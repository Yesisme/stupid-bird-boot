package com.epsoft.demo.netty.shengsiyuan.httpExample;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
/**
 * http服务器
 * @author hp
 *
 */
public class TestServer {

	public static void main(String[] args) throws InterruptedException {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup wordGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap serverBootstrap = new ServerBootstrap();
			serverBootstrap.group(bossGroup, wordGroup).channel(NioServerSocketChannel.class).childHandler(new TestServerInitializer());
			ChannelFuture channelFuture = serverBootstrap.bind(7777).sync();
			channelFuture.channel().closeFuture().sync();
		}finally {
			bossGroup.shutdownGracefully();
			wordGroup.shutdownGracefully();
		}
		
		
	} 
}
