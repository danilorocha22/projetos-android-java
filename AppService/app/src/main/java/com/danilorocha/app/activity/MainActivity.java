package com.danilorocha.app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.danilorocha.app.R;
import com.danilorocha.app.service.Service2;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }//onCreate

    public void click(View view) {
        Intent it = new Intent(this, Service2.class);
        startService(it);
    }//click

}//classe