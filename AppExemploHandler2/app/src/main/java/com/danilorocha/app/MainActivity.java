package com.danilorocha.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView textViewNumber;
    private Button buttonClicar;
    private Handler handler;
    private Integer numberG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }//onCreate

    public void click(View view) {
        switch (view.getId()) {
            case R.id.btnClick:
                Log.i("Main", String.valueOf(Thread.currentThread()));
                handler = new MyHandler();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Random random = new Random();
                        for (int i = 0; i < 10; i++) {
                            Log.i("Thread", String.valueOf(Thread.currentThread()));
                            int num = random.nextInt(21);

                            try {
                                Thread.sleep(10000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            Message msg = new Message();
                            msg.arg1 = num;
                            handler.sendMessage(msg); //envia o número gerado
                        }//for
                    }
                }).start();
        }//switch
    }//click

    private class MyHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            textViewNumber.setText(String.valueOf(msg.arg1)); //obtêm o número gerado através do objeto Message
            Log.i("Msg", String.valueOf(Thread.currentThread()));
        }//metodo
    }//classe interna

}//classe