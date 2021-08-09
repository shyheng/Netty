package org.shy.NIOSocket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

public class Server {
    public static void main(String[] args) throws IOException {
//        使用nio模拟阻塞 单线程
//        0，ByteBuffer
        ByteBuffer buffer = ByteBuffer.allocate(16);
//        1，创建服务器
        ServerSocketChannel ssc = ServerSocketChannel.open();
//        2，绑定监听事件
        ssc.bind(new InetSocketAddress(5555));
//        3，连接集合
        List<SocketChannel> channels = new ArrayList<>();
        while (true){
            SocketChannel sc = ssc.accept();//阻塞方式，线程停止运行
            channels.add(sc);
            for (SocketChannel channel : channels){
//                5，接收客服端发送数据
                channel.read(buffer);//阻塞方式，线程停止运行
                buffer.flip();
                buffer.clear();
            }
        }
    }
}
