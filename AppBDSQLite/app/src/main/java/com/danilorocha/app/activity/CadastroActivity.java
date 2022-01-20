package com.danilorocha.app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.danilorocha.app.R;
import com.danilorocha.app.database.BDSQLite;
import com.danilorocha.app.model.Pessoa;
import com.google.android.material.textfield.TextInputEditText;

public class CadastroActivity extends AppCompatActivity {
    private BDSQLite bd;
    private TextInputEditText inputNome, inputIdade;
    private Pessoa pessoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        inputNome = findViewById(R.id.inputNome);
        inputIdade = findViewById(R.id.inputIdade);
        bd = new BDSQLite(this);
    }//onCreate

    public void click(View view) {
        if (verificarInputs()) {
            salvarPessoa();
            toast("Cadastrado com sucesso!");
            limparInputs();
        }
    }//click

    private boolean verificarInputs() {
        String nome = inputNome.getText().toString();
        String idade = inputIdade.getText().toString();
        if (nome.isEmpty() && idade.isEmpty()) {
            toast("Informe nome e idade");
        } else if (nome.isEmpty()){
            toast("Informe o nome");
        } else if (idade.isEmpty()){
            toast("Informe a idade");
        } else {
            criarPessoa(nome, idade);
            return true;
        }
        return false;
    }//metodo

    private void criarPessoa(String nome, String idade) {
        pessoa = new Pessoa(nome, Integer.parseInt(idade));
    }//metodo

    private void salvarPessoa() {
        bd.inserirDados(pessoa);
    }//metodo

    private void limparInputs() {
        inputNome.setText("");
        inputIdade.setText("");
        inputNome.requestFocus();
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