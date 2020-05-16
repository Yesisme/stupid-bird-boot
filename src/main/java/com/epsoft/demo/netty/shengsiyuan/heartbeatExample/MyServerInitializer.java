package com.epsoft.demo.netty.shengsiyuan.heartbeatExample;

import java.util.concurrent.TimeUnit;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

public class MyServerInitializer extends ChannelInitializer<SocketChannel>{

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		//空闲检测处理器,读空闲5秒，写空闲10秒，读写空闲10秒
		pipeline.addLast(new IdleStateHandler(5, 7, 10, TimeUnit.SECONDS));
		pipeline.addLast(new MyServerHandler());
	}

}
