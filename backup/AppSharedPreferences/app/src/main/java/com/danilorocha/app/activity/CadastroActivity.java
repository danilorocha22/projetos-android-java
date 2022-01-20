package com.danilorocha.app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.danilorocha.app.R;
import com.danilorocha.app.entity.Usuario;
import com.danilorocha.app.util.Banco;

public class CadastroActivity extends AppCompatActivity {
    private EditText inputLogin, inputSenha;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        inputLogin = findViewById(R.id.inputCadastroLogin);
        inputSenha = findViewById(R.id.inputCadastroSenha);
    }//onCrate

    public void click2(View view) {
        salvarUsuario();
    }//click

    private void salvarUsuario() {
        String login = inputLogin.getText().toString();
        String senha = inputSenha.getText().toString();
        if (!login.isEmpty() && !senha.isEmpty())
            toast(Banco.salvarUsuario(login, senha));
        else
            toast("Informe todos os campos");
    }//metodo

    private void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }//metodo

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }//metodo

}//classe