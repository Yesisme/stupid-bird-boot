package com.epsoft.demo.netty.shengsiyuan.heartbeatExample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class MyServerHandler extends ChannelInboundHandlerAdapter{

	//事件监听器
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		if(evt instanceof IdleStateEvent) {
			IdleStateEvent event = (IdleStateEvent)evt;
			String eventType = null;
			switch (event.state()) {
			case READER_IDLE:
				eventType = "读空闲";
				break;

			case WRITER_IDLE:
				eventType = "写空闲";
				break;
			case ALL_IDLE:
				eventType = "读写空闲";
				break;
			default:
				break;
			}
			log.info("服务器:{}",ctx.channel().remoteAddress()+" 超时事件 ："+eventType);
			ctx.channel().close();
		}
	}
}
