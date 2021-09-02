package org.shy.NIOFuture;

import io.netty.channel.EventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.DefaultPromise;

import java.util.concurrent.ExecutionException;

public class TestNettyPromise {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

//        1.准备EventLoop对象
        EventLoop loop = new NioEventLoopGroup().next();

//        2 可以主动创建promise 结果容器
        DefaultPromise<Integer> promise = new DefaultPromise<>(loop);

        new Thread(()->{
//           3 执行一个线程，填充结果
            System.out.println("开始准备");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            promise.setSuccess(80);
        }).start();

        System.out.println(promise.get());
    }
}
