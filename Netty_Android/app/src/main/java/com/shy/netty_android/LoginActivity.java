package com.shy.netty_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class LoginActivity extends AppCompatActivity {

    public static String name;
    public static Socket socket = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText names = findViewById(R.id.name);

        Button login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        try {
                            socket = new Socket("172.20.37.102",8888);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        OutputStream os;
                        try {
                            os = socket.getOutputStream();
                            name = names.getText().toString();
                            os.write(names.getText().toString().getBytes());

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}