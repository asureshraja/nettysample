package com.company;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.router.Handler;
import io.netty.handler.codec.http.router.Router;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;

/**
 * Created by suresh on 9/1/15.
 */
public class PipelineInitializer extends ChannelInitializer<SocketChannel> {
	private static final Router router = new Router()
			.GET("/hello", SampleHandler.class);
	private static final Handler handler = new Handler(router);
	public void initChannel(SocketChannel ch) {
		int poolSize     = 4;
		EventExecutorGroup httpExecutorThreadPool = new DefaultEventExecutorGroup(poolSize);
		EventExecutorGroup handlerExecutorThreadPool = new DefaultEventExecutorGroup(poolSize);

		//fast
//		ChannelPipeline p = ch.pipeline()
//				.addLast(new HttpServerCodec())
//				.addLast(handler.name(), handler);

		//slow
		ChannelPipeline p = ch.pipeline()
				.addLast(httpExecutorThreadPool,new HttpServerCodec())
				.addLast(handlerExecutorThreadPool,handler.name(), handler);
	}
}