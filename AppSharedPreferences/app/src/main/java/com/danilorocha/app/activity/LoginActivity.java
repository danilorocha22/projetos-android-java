package com.danilorocha.app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.danilorocha.app.R;
import com.danilorocha.app.model.Usuario;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {
    private TextInputEditText inputLogin, inputSenha;
    private SharedPreferences preferences;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inputLogin = findViewById(R.id.inputLogin);
        inputSenha = findViewById(R.id.inputSenha);
    }//onCreate

    private boolean criarUsuario() {
        String login = inputLogin.getText().toString();
        String senha = inputSenha.getText().toString();
        if (!login.isEmpty() && !senha.isEmpty()) {
            usuario = new Usuario(login, senha);
            return true;
        }
        return false;
    }//metodo

    private void salvarUsuario() {
        preferences = getSharedPreferences("arquivo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("login", usuario.getLogin());
        editor.putString("senha", usuario.getSenha());
        editor.commit();
    }//metodo

    private void home() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }//metodo

    public void click(View view) {
        if (criarUsuario()) {
            salvarUsuario();
            home();
        } else {
            Toast.makeText(this, "Informe todos os dados", Toast.LENGTH_SHORT).show();
        }
    }//click

}//classe