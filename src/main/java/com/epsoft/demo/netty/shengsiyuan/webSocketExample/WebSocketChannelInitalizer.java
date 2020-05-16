package com.epsoft.demo.netty.shengsiyuan.webSocketExample;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class WebSocketChannelInitalizer extends ChannelInitializer<SocketChannel>{

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		pipeline.addLast(new HttpServerCodec());
		//块方式写入处理器
		pipeline.addLast(new ChunkedWriteHandler());
		//对httpMessage或者httpContent聚合到FullHttpRequest
		//采取分块或者分段的方式 参数：最大聚合长度
		//@param maxContentLength the maximum length of the aggregated content in bytes.
		pipeline.addLast(new HttpObjectAggregator(8961));
		//webSocket服务协议处理器，
		//It takes care of websocket handshaking as well as processing of control frames (Close, Ping, Pong). Text and Binary
		//ws://localhost:port/ws
		pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
		
		pipeline.addLast(new TextWebSocketFrameHanler());
	}

}
