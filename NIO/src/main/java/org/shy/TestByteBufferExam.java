package org.shy;

import java.nio.ByteBuffer;

public class TestByteBufferExam {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(32);
        byteBuffer.put("Hello,world\nI`m zhangsan\nHo".getBytes());
        split(byteBuffer);
        byteBuffer.put("wo are you?\n".getBytes());
        split(byteBuffer);
    }

    private static void split(ByteBuffer byteBuffer) {
        byteBuffer.flip();
        for (int i = 0; i < byteBuffer.limit(); i++) {
            if (byteBuffer.get(i) == '\n'){
                int length = i + 1 - byteBuffer.position();
                ByteBuffer target = ByteBuffer.allocate(length);

                for (int j = 0; j < length; j++) {
                    target.put(byteBuffer.get());
                }
                while (target.hasRemaining()){
                    System.out.println(target.get());
                }
            }
        }
        byteBuffer.compact();
    }
}
