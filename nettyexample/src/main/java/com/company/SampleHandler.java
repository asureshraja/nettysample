package com.company;

/**
 * Created by suresh on 9/1/15.
 */

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.router.KeepAliveWrite;

public class SampleHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
	private static final byte[] CONTENT = { 'H', 'e', 'l', 'l', 'o', ' ', 'W', 'o', 'r', 'l', 'd' };

	@Override
	protected void channelRead0 (ChannelHandlerContext channelHandlerContext, FullHttpRequest fullHttpRequest) throws Exception {
		if(fullHttpRequest instanceof FullHttpRequest){
			FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,HttpResponseStatus.OK, Unpooled.wrappedBuffer(CONTENT));
			response.headers().set(HttpHeaders.Names.CONTENT_TYPE, "text/plain");
			response.headers().set(HttpHeaders.Names.CONTENT_LENGTH, response.content().readableBytes());
			response.headers().set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
			KeepAliveWrite.flush(channelHandlerContext,fullHttpRequest,  response);
		}
	}



}
