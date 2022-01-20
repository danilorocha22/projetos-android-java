package com.danilorocha.app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.danilorocha.app.R;
import com.danilorocha.app.entity.Usuario;
import com.danilorocha.app.util.Banco;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {
    private EditText inputLogin, inputSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputLogin = (EditText) findViewById(R.id.editTextLogin);
        inputSenha = (EditText) findViewById(R.id.editTextSenha);
    }//onCreate

    public void click(View view) {
        switch (view.getId()) {
            //cadastrar
            case R.id.btnCadastrar:
                telaDeCadastro();
                break;

            //entrada de dados
            case R.id.btnLogin:
                receberDados();
        }//switch
    }//click

    private void telaDeCadastro() {
        Intent intent = new Intent(this, CadastroActivity.class);
        startActivity(intent);
    }//metodo

    private void receberDados() {
        String usuario = inputLogin.getText().toString();
        String senha = inputSenha.getText().toString();
        String msg = (usuario.isEmpty() && senha.isEmpty()) ? "Informe todos os campos" : validarUsuario(usuario, senha);
        toast(msg);
    }//metodo

    private String validarUsuario(String user, String senha) {
        Usuario usuario = Banco.pegarUsuario(user);
        if (!usuario.getLogin().equals(user)) {
            return "Login não confere";
        } if (!usuario.getSenha().equals(senha)) {
            return "Senha não confere";
        } else {
            home(usuario);
        }
        return "";
    }//metodo

    private void home(Usuario usuario) {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("usuario", (Serializable) usuario);
        startActivity(intent);
        inputLogin.setText("");
        inputSenha.setText("");
    }//metodo

    private void cadastrar() {
    }//metodo

    private void toast(String msg) {
        if (!msg.isEmpty())
            Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
    }//metodo

}//classe