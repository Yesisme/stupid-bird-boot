package com.epsoft.demo.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;


//netty主程序
public class HelloServer {
 
	public static void main(String... args) throws Exception {
		//两个工作组
		NioEventLoopGroup bossGroup = new NioEventLoopGroup();//负责接收客户端连接
		NioEventLoopGroup  workerGroup= new NioEventLoopGroup();//处理连接（来自boss线程的连接）
		
		try{
			//启动服务端
			ServerBootstrap bootstrap= new ServerBootstrap();
			bootstrap.group(bossGroup,workerGroup)
			.option(ChannelOption.SO_BACKLOG,1024)
			//NioServerSocketChannel就是调用jdk底层的newSocket去创建一个socket
			//通过NioServerSocketChannelConfig()就是tcp参数的配置类
			//通过NioServerSocketChannelConfig()构造函数中的ch.configureBlocking(false);非阻塞
			//通过AbstractChannel传教id pinple
            .channel(NioServerSocketChannel.class)//告诉channel如何接收连接 用于构造socketchannel工厂
            .childHandler(new ChannelInitializer<Channel>() { //事件channel，childHandler子处理器，实现自定义的逻辑
				@Override
				protected void initChannel(Channel ch) throws Exception {
					//连接的一个管道
					ChannelPipeline pipeline = ch.pipeline();					
					//addLast增加到最后 负载http 负责编码解码，
					pipeline.addLast(new HttpServerCodec());					
					//实际处理业务的类HelloServerHandler
					pipeline.addLast(new HelloServerHandler());		
				}
            	
			}).option(ChannelOption.SO_BACKLOG, 128);
			
			//绑定端口，开始接收进来的连接
			ChannelFuture channelFuture = bootstrap.bind(9999).sync();
			//关闭监听
			channelFuture.channel().closeFuture().sync();
		}finally{
			//优雅关闭
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}		
	}
}
