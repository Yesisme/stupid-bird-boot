package com.epsoft.demo.netty;

import java.net.URI;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;

//具体处理类
public class HelloServerHandler extends ChannelInboundHandlerAdapter {
	
	private static final String CONTENT_TYPE = "contentType";
	private static final String CONTENT_LENGTH = "contentlength";
	
	//读取客户端发送过来的请求，并响应的方法
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
	    System.out.println("--------------httpserverHandler, remote_address " + ctx.channel().remoteAddress() + ", msg_class:" + msg.getClass());
		System.out.println("接收到请求：  "+msg);
		if(msg instanceof HttpRequest){
			HttpRequest httpRequest = (HttpRequest) msg;
			ByteBuf bytes = (ByteBuf) msg;
			System.out.println("bytes:"+bytes);
		    URI uri = new URI(httpRequest.getUri());
			System.out.println("请求方式:"+httpRequest.getMethod()+"<-->"+"请求路径:"+uri.getPath());//接收到的请求方式
			//构造响应内容字符串
			ByteBuf content = Unpooled.copiedBuffer("this is helloworld", CharsetUtil.UTF_8);
			//构造响应格式
			DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK,content);
			//http请求头信息
			response.headers().set(CONTENT_TYPE,"text/plain");
			//设置http响应长度
			response.headers().set(CONTENT_LENGTH,content.readableBytes());  
			//响应长度
			byte[] bt = new byte[content.readableBytes()];
            
            System.out.println("响应给客户端对象: " + response);
            
            ctx.writeAndFlush(response);
            ctx.channel().closeFuture();
		}
	}	
}
