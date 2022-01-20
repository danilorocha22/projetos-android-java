package com.danilorocha.app.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.danilorocha.app.model.Pessoa;

import java.util.ArrayList;
import java.util.List;

public class BDSQLite extends SQLiteOpenHelper {
    public static final String BD_NAME = "bd_sqlite";
    public static final int BD_VERSION = 1;
    private static final String QUERY_SELECT = "SELECT ID, NOME, IDADE FROM TB_PESSOA ORDER BY ID";

    public BDSQLite(@Nullable Context context) {
        super(context, BD_NAME, null, BD_VERSION);
    }//construtor

    /**
     * Responsável por GERAR A TABELA NO BANCO
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder query = new StringBuilder();
        query.append("CREATE TABLE TB_PESSOA(");
        query.append("ID INTEGER PRIMARY KEY AUTOINCREMENT,");
        query.append("NOME TEXT NOT NULL,");
        query.append("IDADE INT NOT NULL)");
        db.execSQL(query.toString());
    }//metodo

    /**
     * Responsável por ATUALIZAR A TABELA NO BANCO, quando necessário
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }//metodo

    /**
     * Responsável por INSERIR DADOS NO BANCO
     * Ao executar o método getWritableDatabase(), o método onCreate() é executado.
     * O método getWritableDatabase() é utilizado para as operações de insert, delete e update.
     */
    public void inserirDados(Pessoa pessoa) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("NOME", pessoa.getNome());// COLUNA | VALOR
        values.put("IDADE", pessoa.getIdade());
        db.insert("TB_PESSOA", null, values);
    }//metodo

    /**
     * Responsável por CONSULTAR DADOS NO BANCO
     * Para consultar dados, utilizamos o método getReadableDatabase()
     */
    public List<Pessoa> consultarPessoas() {
        try (SQLiteDatabase db = getReadableDatabase();
             Cursor cursor = db.rawQuery(QUERY_SELECT, null)) {
            List<Pessoa> pessoas = new ArrayList<>();
            if (cursor.moveToFirst()) {
                do {
                    Pessoa p = new Pessoa();
                    p.setId(cursor.getInt(cursor.getColumnIndex("ID")));
                    p.setNome(cursor.getString(cursor.getColumnIndex("NOME")));
                    p.setIdade(cursor.getInt(cursor.getColumnIndex("IDADE")));
                    pessoas.add(p);
                } while (cursor.moveToNext());
            }//if
            cursor.close();
            return pessoas;
        }//try
    }//metdo

    /**
     * Para retornar uma pessoa pelo ID
     */
    public Pessoa consultarPessoaById(int id) {
        List<Pessoa> pessoas = consultarPessoas();
        Pessoa pessoa = new Pessoa();
        pessoas.stream().forEach(p -> {
            if (p.getId() == id) {
                pessoa.setId(p.getId());
                pessoa.setNome(p.getNome());
                pessoa.setIdade(p.getIdade());
            }
        });
        return pessoa;
    }//metodo

    /**
     * Responsável por EXCLUIR DADOS NO BANCO
     */
    public void excluir(int id) {
        SQLiteDatabase db = getReadableDatabase();
        String selection = "ID LIKE?";// Define 'where' part of query.
        String[] selectionArgs = {String.valueOf(id)};// Specify arguments in placeholder order.
        db.delete("TB_PESSOA", selection, selectionArgs);// Issue SQL statement.
    }//metodo

    public void update(Pessoa pessoa) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("NOME", pessoa.getNome());// COLUNA | VALOR
        values.put("IDADE", pessoa.getIdade());
        String selection = "ID LIKE?";
        String[] selectionArgs = {String.valueOf(pessoa.getId())};

        int count = db.update(
                "TB_PESSOA",
                values,
                selection,
                selectionArgs
        );
    }//metodo

}//classe
