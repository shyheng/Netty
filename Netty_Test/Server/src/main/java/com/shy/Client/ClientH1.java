package com.shy.Client;


import com.shy.ClientRW.ClientR;
import com.shy.ClientRW.ClientW;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientH1 {
    public static void main(String[] args) throws IOException {

        Socket socket = null;
        try {
            socket = new Socket("127.0.0.1",8888);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入名字");
        String name = scanner.next();

        OutputStream os = socket.getOutputStream();
        os.write(name.getBytes());

        Thread threadR = new Thread(new ClientR(socket));
        Thread threadW = new Thread(new ClientW(socket,name));
        threadR.start();
        threadW.start();

    }
}
