package com.company;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;

/**
 * Created by suresh on 9/1/15.
 */
public class PipelineInitializer extends ChannelInitializer<SocketChannel> {

	public void initChannel(SocketChannel ch) {
		int poolSize     = 4;
		EventExecutorGroup httpExecutorThreadPool = new DefaultEventExecutorGroup(poolSize);

		//fast
		ChannelPipeline p = ch.pipeline()
				.addLast(new HttpServerCodec())
				.addLast(new HttpObjectAggregator(1024*1024))
				.addLast(new SampleHandler());



		//slow
//		ChannelPipeline p = ch.pipeline()
//				.addLast(new HttpServerCodec())
//				.addLast(new HttpObjectAggregator(1024*1024))
//				.addLast(httpExecutorThreadPool,new SampleHandler());

	}
}
