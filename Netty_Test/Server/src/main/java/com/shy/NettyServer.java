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
//                                        String[] s = msg.toString().split(" ");
//                                        if (s.length == 1) {
//                                            userChannel.put(s[0], ctx.channel());
//                                        } else {
//                                            String write = s[0] + " " + s[2];
//                                            userChannel.get(s[1]).writeAndFlush(userChannel.get(s[1]).alloc().buffer().writeBytes(write.getBytes()));
//                                        }
//
//                                        for (int i = 0; i < group.size(); i++) {
//                                            groups.get("s").get(i).writeAndFlush(groups.get("s").get(i).alloc().buffer().writeBytes("信息".getBytes()));
//                                        }
                                        String[] s = msg.toString().split(" ");
                                        if (s.length == 1) {
                                            userChannel.put(s[0], ctx.channel());
                                        }else {
                                            switch (s[1]){
                                                case "add":
                                                    groups.put(s[2],new ArrayList<>());
                                                    groups.get(s[2]).add(ctx.channel());
                                                    break;
                                                case "go": ;
                                                    groups.get(s[2]).add(ctx.channel());
                                                    break;
                                                case "send":

                                                    String write = s[0] + " " + s[3];

                                                    for (int i = 0; i < groups.get(s[2]).size(); i++) {

                                                        if (!(groups.get(s[2]).get(i).equals(ctx.channel()))){
                                                            groups.get(s[2]).get(i).writeAndFlush(groups.get(s[2]).get(i).alloc().buffer().writeBytes(write.getBytes()));
                                                        }
                                                    }
                                                    break;
                                                case "remove":
                                                    groups.get(s[2]).remove(s[1]);
                                                    break;
                                            }
                                        }
                                    }
                                });
                            }
                        }).bind(8888);
    }

}
