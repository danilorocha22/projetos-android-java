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
    private Button btnClick;
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
                Random random = new Random();
                handler = new Handler();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 10; i++) {
                            Log.i("Thread", String.valueOf(Thread.currentThread()));
                            numberG = random.nextInt(21);

                            try {
                                Thread.sleep(10000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Log.i("Post", String.valueOf(Thread.currentThread()));
                                    textViewNumber.setText(String.valueOf(numberG)); //atualizando o conteúdo do TextView com o número aleatório gerado.
                                }
                            });
                        }//for
                    }
                }).start();
        }//switch
    }//click

}//classe