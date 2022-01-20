package com.danilorocha.imc.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.danilorocha.imc.R;
import com.danilorocha.imc.activity.MainActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Desabilita a ActionBar
        getSupportActionBar().hide();

        //Chama a MainActivity depois de algun tempo
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        },6000);

    }//metodo

    public Context getActivity() {
        return this;
    }//metodo
}