package com.danilorocha.app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.danilorocha.app.R;
import com.danilorocha.app.model.Usuario;

public class HomeActivity extends AppCompatActivity {
    private TextView textView;
    private SharedPreferences preferences;
    private String login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        textView = findViewById(R.id.textViewUsuario);
        preferences = getSharedPreferences("arquivo", Context.MODE_PRIVATE);
        login = preferences.getString("login", null);
        textView.setText("Usuário: "+ login);
    }//metodo

}//classe