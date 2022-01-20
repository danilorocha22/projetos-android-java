package com.danilo.crudjavaandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

@SuppressWarnings("all")
public class AlunoDAO {
    private Conexao conexao;
    private SQLiteDatabase database;

    public AlunoDAO(Context context) {
        conexao = new Conexao(context);
        database = conexao.getWritableDatabase();
    }

    public Long inserir(Aluno aluno) {
        ContentValues values = new ContentValues();
        values.put("nome", aluno.getNome());
        values.put("cpf", aluno.getCpf());
        values.put("fone", aluno.getFone());
        return database.insert("aluno", null, values);
    }
}
