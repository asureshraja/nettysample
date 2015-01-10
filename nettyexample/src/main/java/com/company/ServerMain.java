package com.company;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class ServerMain {
    private int port;

    public ServerMain(int port) {
        this.port = port;
    }

    public void run() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new PipelineInitializer());
            b.childOption(ChannelOption.TCP_NODELAY, java.lang.Boolean.TRUE);
            b.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
            b.option(ChannelOption.SO_REUSEADDR, true);
            b.option(ChannelOption.SO_KEEPALIVE, false);
            b.option(ChannelOption.SO_RCVBUF, 52428800);
            b.option(ChannelOption.SO_LINGER, 0);

            // Bind and start to accept incoming connections.
            ChannelFuture f = b.bind(port).sync();
            f.channel().closeFuture().sync();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        int port = 8080;
        new ServerMain(port).run();
    }
}
