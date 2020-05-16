package com.epsoft.demo.netty.shengsiyuan.tcpExample;

import java.util.UUID;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class MyServerHandler extends SimpleChannelInboundHandler<String>{

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		log.info("接收到客户端消息{}",ctx.channel().remoteAddress()+":"+ msg);
		ctx.channel().writeAndFlush("from server"+UUID.randomUUID());
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
	
}
