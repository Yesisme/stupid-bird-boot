package com.epsoft.demo.netty.shengsiyuan.httpExample;

import java.net.URI;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject>{
 
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
		log.info("msg{}",msg.getClass());
		log.info("remoteaddress{}:",ctx.channel().remoteAddress());
		Thread.sleep(8000);
		if(msg instanceof HttpRequest) {
			HttpRequest httpRequest = (HttpRequest)msg;
			log.info("请求的方法名:{}",httpRequest.method().name());
			URI url = new URI(httpRequest.uri());
			if("/favicon.ico".equals(url.getPath())) {
				System.out.println("favicon.ico");
				return;
			}
			ByteBuf content = Unpooled.copiedBuffer("Hello world", CharsetUtil.UTF_8);
			FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK,content);
			response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
			response.headers().set(HttpHeaderNames.CONTENT_LENGTH,content.readableBytes());
			ctx.writeAndFlush(response);
			//手动关闭，基于http 1.1会延迟关闭
			ctx.channel().close();
		}
		
	}
	
	//连接处与活动状态
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		log.info("channel Active{}:","---------");
		super.channelActive(ctx);
	}
	
	//连接注册
	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		log.info("channel registry{}","----------");
		super.channelRegistered(ctx);
	}
	
	//连接建立
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		log.info("handler add {}","-------------");
		super.handlerAdded(ctx);
	}
	
	//连接不活跃
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		log.info("channel in active{}","------------------");
		super.channelInactive(ctx);
	}
	
	//失去连接
	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		log.info("channle unRegistry{}");
		super.channelUnregistered(ctx);
	}

	//连接失去了
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		log.info("channel remove");
		super.handlerRemoved(ctx);
	}
}
