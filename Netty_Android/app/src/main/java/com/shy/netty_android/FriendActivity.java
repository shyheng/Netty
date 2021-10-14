package com.shy.netty_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FriendActivity extends AppCompatActivity {
    ListView listView;
    List<String> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);
        listView = findViewById(R.id.friend_take);
        Button send = findViewById(R.id.send);
        EditText f_send = findViewById(R.id.friend_send);
        rend();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        OutputStream os;
                        try {
                            os = LoginActivity.socket.getOutputStream();
                            String msg;
                            String write;
                            msg = f_send.getText().toString();
                            write = LoginActivity.name + " " + MainActivity.Friend + " " + msg;


//                    System.out.println("输入指令");
//                    String instruction = scanner.next();
//                    String group = scanner.next();
//                    String write;
//                    if (instruction.equals("send")){
//                        String msg = scanner.next();
//                        write = name + " " + instruction + " " + group + " " + msg;
//                    }else{
//                        write = name + " " + instruction + " " + group;
//                    }

                            os.write(write.getBytes());
                        } catch (IOException e) {
                            return;
                        }
                    }
                }.start();
            }
        });
    }

    public void rend() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                InputStream inputStream = null;
                try {
                    inputStream = LoginActivity.socket.getInputStream();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                byte[] bytes = new byte[1024];
                int len ;
                while (true) {
                    try {
                        len = inputStream.read(bytes);
                    } catch (IOException e) {
                        return;
                    }
                    String read = new String(bytes, 0, len);
                    String[] split = read.split(" ");
                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String time = sdf.format(date);

                    list.add(time + "\n" + split[0] + "说" + split[1]);
                    System.out.println(list.get(0));

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                                    android.R.layout.simple_list_item_1, list);
                            System.out.println(list.get(0));
                            listView.setAdapter(adapter);
                        }
                    });
                }


            }
        }.start();
    }
}