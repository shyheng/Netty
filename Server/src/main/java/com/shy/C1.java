package com.shy;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class C1 {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1",8888);
        while (true)
        {
            OutputStream os =   socket.getOutputStream();
            Scanner scanner = new Scanner(System.in);
            String s = scanner.next();

            os.write(s.getBytes());
//            InputStream inputStream = socket.getInputStream();
//            byte[] bytes = new byte[1024];
//            int len = inputStream.read(bytes);
//            System.out.println(new String(bytes,0,len));
        }


//        socket.close();
    }
}
