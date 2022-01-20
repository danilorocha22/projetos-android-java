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

public class AtualizaActivity extends AppCompatActivity {
    private BDSQLite bd;
    private TextInputEditText inputId, inputNome, inputIdade;
    private String id, nome, idade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        inputId = findViewById(R.id.inputIdAtualizar);
        inputNome = findViewById(R.id.inputAtualizarNome);
        inputIdade = findViewById(R.id.inputAtualizarIdade);
        bd = new BDSQLite(this);
    }//onCrate

    private Pessoa buscarPessoa() {
        if (verificarInputId()) {
            int id = Integer.parseInt(this.id);
            String nome =  bd.consultarPessoaById(id).getNome();
            if (nome == null) {
                toast("Pessoa não encontrada");
                limpar();
            } else
                return bd.consultarPessoaById(id);
        }
        return null;
    }//metodo

    private void atualizarPessoa() {
        if (verificarInputsNomeIdade()) {
            bd.update(novaPessoa());
            toast("Atualizado com sucesso!");
            limpar();
        }
    }//metodo

    private Pessoa novaPessoa() {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(Integer.parseInt(id));
        pessoa.setNome(nome);
        pessoa.setIdade(Integer.parseInt(idade));
        return pessoa;
    }//metodo

    private boolean verificarInputId() {
        id = inputId.getText().toString();
        if (!id.isEmpty())
            return true;
        else
            toast("Informe o ID");
        return false;
    }//metodo

    private boolean verificarInputsNomeIdade() {
        if (verificarInputId()) {
            nome = inputNome.getText().toString();
            idade = inputIdade.getText().toString();
            if (nome.isEmpty() && idade.isEmpty()) {
                toast("Informe nome e idade");
            } else if (nome.isEmpty()) {
                toast("Informe o nome");
            } else if (idade.isEmpty()) {
                toast("Informe a idade");
            } else {
                return true;
            }//else
        }//if
        return false;
    }//metodo

    private void atualizarInputs(Pessoa pessoa) {
        inputNome.setText(pessoa.getNome());
        inputIdade.setText(String.valueOf(pessoa.getIdade()));
    }//metodo

    private void limpar() {
        inputId.setText("");
        inputNome.setText("");
        inputIdade.setText("");
        inputId.requestFocus();
    }//metodo

    private void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }//metodo

    public void click(View view) {
        switch (view.getId()) {
            case R.id.btnPesquisar:
                if (buscarPessoa() != null)
                    atualizarInputs(buscarPessoa());
                break;

            case R.id.btnAtualizar:
                atualizarPessoa();
                break;
        }//switch
    }//click

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