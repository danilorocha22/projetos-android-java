package com.danilo.crudjavaandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

@SuppressWarnings("all")
public class CadastroAlunoActivity extends AppCompatActivity {

    private EditText nome;
    private EditText cpf;
    private EditText fone;
    private AlunoDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nome = findViewById(R.id.inputNome);
        cpf = findViewById(R.id.inputCPF);
        fone = findViewById(R.id.inputFone);
        dao = new AlunoDAO(this);
    }

    public void salvar() {
        Aluno aluno = new Aluno();
        aluno.setNome(nome.getText().toString());
        aluno.setCpf(cpf.getText().toString());
        aluno.setFone(fone.getText().toString());
        Long id = dao.inserir(aluno);
        Toast.makeText(this, "Aluno criado com id: "+ id, Toast.LENGTH_SHORT).show();
    }
}