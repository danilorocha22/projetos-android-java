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

public class ExcluiActivity extends AppCompatActivity {
    private BDSQLite bd;
    private TextInputEditText inputId;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excluir);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        bd = new BDSQLite(this);
        inputId = findViewById(R.id.inputId);
    }//onCrate

    private boolean verificarInputs() {
        id = inputId.getText().toString();
        if (!id.isEmpty())
            return true;
        else
            toast("Informe o ID");
        return false;
    }//metodo

    private void verificarPessoaCadastrada() {
        int id = Integer.parseInt(this.id);
        String nome = bd.consultarPessoaById(id).getNome();
        if (nome == null)
            toast("Pessoa não encontrada");
        else
            excluirPessoa(id);
    }//metodo

    private void excluirPessoa(int id) {
        bd.excluir(id);
        toast("Excluído com sucesso!");
    }//metodo

    private void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }//metodo

    public void click(View view) {
        if (verificarInputs()) {
            verificarPessoaCadastrada();
            inputId.setText("");
        }//if
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