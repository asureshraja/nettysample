package com.company;

/**
 * Created by suresh on 9/1/15.
 */

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.router.KeepAliveWrite;
import io.netty.handler.codec.http.router.Routed;

public class SampleHandler implements ChannelInboundHandler {
	private static final byte[] CONTENT = { 'H', 'e', 'l', 'l', 'o', ' ', 'W', 'o', 'r', 'l', 'd' };

	@Override
	public void channelRegistered (ChannelHandlerContext channelHandlerContext) throws Exception {

	}

	@Override
	public void channelUnregistered (ChannelHandlerContext channelHandlerContext) throws Exception {

	}

	@Override
	public void channelActive (ChannelHandlerContext channelHandlerContext) throws Exception {

	}

	@Override
	public void channelInactive (ChannelHandlerContext channelHandlerContext) throws Exception {

	}

	@Override
	public void channelRead (ChannelHandlerContext channelHandlerContext, Object obj) throws Exception {
			if(obj instanceof Routed){
				Routed routed = (Routed)obj;
				FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,HttpResponseStatus.OK, Unpooled.wrappedBuffer(CONTENT));
				response.headers().set(HttpHeaders.Names.CONTENT_TYPE, "text/plain");
				response.headers().set(HttpHeaders.Names.CONTENT_LENGTH, response.content().readableBytes());
				//response.headers().set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
				KeepAliveWrite.flush(channelHandlerContext,routed.request(),  response);
			}
	}

	public void channelReadComplete(ChannelHandlerContext ctx) {
		ctx.flush();
	}

	@Override
	public void userEventTriggered (ChannelHandlerContext channelHandlerContext, Object o) throws Exception {

	}

	@Override
	public void channelWritabilityChanged (ChannelHandlerContext channelHandlerContext) throws Exception {

	}

	@Override
	public void handlerAdded (ChannelHandlerContext channelHandlerContext) throws Exception {

	}

	@Override
	public void handlerRemoved (ChannelHandlerContext channelHandlerContext) throws Exception {

	}

	@Override
	public void exceptionCaught (ChannelHandlerContext channelHandlerContext, Throwable throwable) throws Exception {

	}



}