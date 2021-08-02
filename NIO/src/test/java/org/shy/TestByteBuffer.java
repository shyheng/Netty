package org.shy;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class TestByteBuffer {
    public static void main(String[] args) {
        try (FileChannel channel = new FileInputStream("E:\\Netty\\NIO\\src\\shy").getChannel()) {
//            缓存数据区
            ByteBuffer byteBuffer = ByteBuffer.allocate(10);
            while (true){
                //            读取数据
                int len = channel.read(byteBuffer);
                if (len == -1){
                    break;
                }
//            打印buffer的内容
                byteBuffer.flip();//切换读模式
                while (byteBuffer.hasRemaining()){
                    byte b = byteBuffer.get();
                    System.out.println((char) b);
                }
//                切换写模式
                byteBuffer.clear();
            }

        } catch (IOException e) {
        }
    }
}
