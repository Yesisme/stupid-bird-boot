package com.epsoft.demo.netty.shengsiyuan.tcpExample;

import java.time.LocalDateTime;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class MyClientHandler extends SimpleChannelInboundHandler<String>{

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		log.info("服务端地址{}",ctx.channel().remoteAddress());
		log.info("client outPut {}",msg);
		ctx.writeAndFlush("from client"+LocalDateTime.now());
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ctx.writeAndFlush("来自客户端的问候{}");
	}
}
