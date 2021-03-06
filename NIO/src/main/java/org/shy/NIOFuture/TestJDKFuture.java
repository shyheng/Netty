package org.shy.NIOFuture;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestJDKFuture {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2);
        Future<Integer> future = service.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 50;
            }
        });
    }
}
