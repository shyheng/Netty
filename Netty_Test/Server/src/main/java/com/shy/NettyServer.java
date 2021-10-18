package com.shy;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NettyServer {

    public static void main(String[] args) {
        Map<String, Channel> userChannel = new HashMap<>();
        List<Channel> group = new ArrayList<>();
        Map<String,List<Channel>> groups = new HashMap<>();
        new ServerBootstrap()
                .group(new NioEventLoopGroup())
                .channel(NioServerSocketChannel.class)
                .childHandler(
                        new ChannelInitializer<NioSocketChannel>() {
                            @Override
                            protected void initChannel(NioSocketChannel nsc) throws Exception {
                                nsc.pipeline().addLast(new StringDecoder());//将ByteBuffer转换为字符串
                                nsc.pipeline().addLast(new ChannelInboundHandlerAdapter() {//自定义handler
                                    @Override
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                        String[] info = msg.toString().split(" ");
                                        if (info.length == 1) {
                                            userChannel.put(info[0], ctx.channel());
                                        } else {
                                           if (info[1].equals("chat")){
                                               String write = info[0] + " " + info[3];
                                               userChannel.get(info[2]).writeAndFlush(userChannel.get(info[2]).alloc().buffer().writeBytes(write.getBytes()));
                                           }else {
                                               switch (info[2]){
                                                case "add":
                                                    groups.put(info[3],new ArrayList<>());
                                                    groups.get(info[3]).add(ctx.channel());
                                                    break;
                                                case "go": ;
                                                    groups.get(info[3]).add(ctx.channel());
                                                    break;
                                                case "send":

                                                    String write = info[0] + " " + info[4];

                                                    for (int i = 0; i < groups.get(info[3]).size(); i++) {

                                                        if (!(groups.get(info[3]).get(i).equals(ctx.channel()))){
                                                            groups.get(info[3]).get(i).writeAndFlush(groups.get(info[3]).get(i).alloc().buffer().writeBytes(write.getBytes()));
                                                        }
                                                    }
                                                    break;
                                                case "remove":
                                                    groups.get(info[3]).remove(ctx.channel());
                                                    break;
                                            }
                                           }
                                        }
                                    }
                                });
                            }
                        }).bind(8888);
    }

}
