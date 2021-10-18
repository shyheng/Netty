package com.shy.ClientRW;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientW implements Runnable {
    Socket socket;
    String name;

    public ClientW(Socket socket, String name) {
        this.socket = socket;
        this.name = name;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {

            OutputStream os;
            try {
                os = socket.getOutputStream();
//                String friend;
//                String msg;
//                String write;
//                System.out.println("请输入对方名字和消息");
//                friend = scanner.next();
//                msg = scanner.next();
//                write = name + " " + friend + " " + msg;
//                System.out.println("输入指令");
//                String instruction = scanner.next();
//                String group = scanner.next();
//                String write;
//                if (instruction.equals("send")){
//                    String msg = scanner.next();
//                    write = name + " " + instruction + " " + group + " " + msg;
//                }else{
//                    write = name + " " + instruction + " " + group;
//                }
                String chat = scanner.next();
                String instruction = scanner.next();
                String group = scanner.next();
                String write;
                if (instruction.equals("send")){
                    String msg = scanner.next();
                    write = name + " " + chat + " " + instruction + " " + group + " " + msg;
                }else{
                    write = name + " " + chat + " " + instruction + " " + group;
                }
                os.write(write.getBytes());
            } catch (IOException e) {
                return;
            }


        }
    }
}
