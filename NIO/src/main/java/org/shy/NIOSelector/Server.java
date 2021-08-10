package org.shy.NIOSelector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Server {
    public static void main(String[] args) throws IOException {
//        创建selector对象
        Selector selector = Selector.open();
        ByteBuffer buffer = ByteBuffer.allocate(16);
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);

//        2 建立selector和channel的联系（注册
//        SelectionKey就是将来事件发生后，通过他就可以知道事件和那个channel的事件
        SelectionKey ssckey = ssc.register(selector,0,null);
//        key只关注ACCEPT
        ssckey.interestOps(SelectionKey.OP_ACCEPT);

        ssc.bind(new InetSocketAddress(8000));

        while (true){
//            3 selector方法
            selector.select();

//            4 处理事件
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                SocketChannel sc= channel.accept();

            }
        }

    }
}
