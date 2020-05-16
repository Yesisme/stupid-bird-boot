package com.epsoft.demo.netty.shengsiyuan.tcpExample;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
/**
 * 
 * @author hp
 *
 */
public class MyClient {

	public static void main(String[] args) throws Exception {
		EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
		
		try {
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new MyClientInitailzer());
			ChannelFuture channelFuture = bootstrap.connect("localhost",8899).sync();
			channelFuture.channel().closeFuture().sync();
		}finally {
			eventLoopGroup.shutdownGracefully();
		}
	}
}
