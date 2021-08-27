package com.shy;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

public class NettyServer {
    public static void main(String[] args) {
//        1 启动器 负责组装netty 启动服务器
        new ServerBootstrap()
//                2 group组
                .group(new NioEventLoopGroup())
//                3 选择服务器 ServerSocketChannel实现
                .channel(NioServerSocketChannel.class)
//                4 boss 负责处理连接 worker(child) 负责读写，决定worker能执行哪些操作
                .childHandler(
//                        5 channel 代表和客服端进行数据读写通道 Initialize初始化，负责添加别的Handler
                        new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nsc) throws Exception {
//                        6 添加具体handler
                        nsc.pipeline().addLast(new StringDecoder());//将ByteBuffer转换为字符串
                        nsc.pipeline().addLast(new ChannelInboundHandlerAdapter(){//自定义handler
                            @Override//读事件
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//                                打印上一步装换好的字符串
                                System.out.println(msg);
                            }
                        });
                    }
//                    绑定监听端口
                }).bind(8888);
    }
}
