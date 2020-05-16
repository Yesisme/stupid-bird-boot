package com.epsoft.demo.netty.shengsiyuan.webSocketExample;

import java.time.LocalDateTime;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class TextWebSocketFrameHanler extends SimpleChannelInboundHandler<TextWebSocketFrame>{
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
		log.info("收到消息{}",msg.text());
		ctx.channel().writeAndFlush(new TextWebSocketFrame("服务器时间"+LocalDateTime.now()));
	}
	
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		log.info("handleAdded{}"+ctx.channel().id().asLongText());
	}
	
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		log.info("handlerRemoved"+ctx.channel().id().asLongText());
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

}
