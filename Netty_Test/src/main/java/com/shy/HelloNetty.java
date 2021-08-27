package com.shy;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;

public class HelloNetty {
    public static void main(String[] args) throws InterruptedException {
//        1 启动类
        new Bootstrap()
//                2 添加EventLoop
                .group(new NioEventLoopGroup())
//                3 选择客服端 channel
        .channel(NioSocketChannel.class)
//                4 添加处理器
        .handler(new ChannelInitializer<NioSocketChannel>() {
            @Override
            protected void initChannel(NioSocketChannel nsc) throws Exception {
                nsc.pipeline().addLast(new StringEncoder());
            }
        })
                .connect(new InetSocketAddress("localhost",8888))
                .sync()
                .channel()
//
                .writeAndFlush("hello shy");
    }
}
