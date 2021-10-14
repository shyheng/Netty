package com.shy.netty_android.client;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientR implements Runnable{
    Socket socket;
    public ClientR(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream inputStream = null;
        try {
            inputStream = socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] bytes = new byte[1024];
        int len = 0;
        while (true){
            try {
                len = inputStream.read(bytes);
            } catch (IOException e) {
                return;
            }
            String read = new String(bytes, 0, len);
            String[] split = read.split(" ");
            Date date = new Date();
            SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss" );
            String time = sdf.format(date);
            System.out.println("____________________");
            System.out.println(time);
            System.out.println(split[0]+"说"+split[1]);
            System.out.println("————————————————————");
        }

    }
}
