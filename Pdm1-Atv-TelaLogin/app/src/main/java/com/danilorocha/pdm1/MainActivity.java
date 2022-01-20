package com.danilorocha.pdm1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

@SuppressWarnings("all")
public class MainActivity extends AppCompatActivity {
    private Usuario usuario;
    private EditText editTextLogin, editTextSenha;
    private Button btnEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextLogin = (EditText) findViewById(R.id.editTextLogin);
        editTextSenha = (EditText) findViewById(R.id.editTextSenha);
        btnEntrar = (Button) findViewById(R.id.btnLogin);
        usuario = new Usuario("danilo", "123");
        btnEntrar.setOnClickListener(verificarLogin(usuario));
    }//metodo

    private Context getActivity() {
        return this;
    }//metodo

    private View.OnClickListener verificarLogin(Usuario usuario) {
        return new Button.OnClickListener() {

            @Override
            public void onClick(View view) {
                String login = editTextLogin.getText().toString();
                String senha = editTextSenha.getText().toString();

                if (login.equals(usuario.getLogin()) && senha.equals(usuario.getSenha())) {
                    logarHome();
                } else {
                    Toast.makeText(
                             getApplicationContext(),
                            "Login ou Senha incorretos!",
                             Toast.LENGTH_LONG).show();
                }
            }
        };
    }//metodo

    private void logarHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("login", usuario.getLogin());
        startActivity(intent);
        editTextLogin.setText("");
        editTextSenha.setText("");
    }//metodo

}//classe