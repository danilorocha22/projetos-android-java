package com.danilorocha.app.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.danilorocha.app.R;
import com.danilorocha.app.model.Usuario;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences preferences;
    private String login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = getSharedPreferences("arquivo", Context.MODE_PRIVATE);
        login = preferences.getString("login", null);

        if (login != null)
            startActivity(HomeActivity.class);
        else
            startActivity(LoginActivity.class);
    }//onCreate

    private void startActivity(Class classe) {
        Intent intent = new Intent(this, classe);
        startActivity(intent);
        finish();
    }//metodo

}//classe