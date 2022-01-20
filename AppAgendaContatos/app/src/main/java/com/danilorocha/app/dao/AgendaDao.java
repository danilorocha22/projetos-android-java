package com.danilorocha.app.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.danilorocha.app.model.Contato;

import java.util.ArrayList;
import java.util.List;

public class AgendaDao extends SQLiteOpenHelper {
    public static final String DB_NAME = "DB_AGENDA";
    public static final int DB_VERSION = 1;
    private static final String QUERY_SELECT =
            "SELECT ID, NOME, FONE, EMAIL FROM TB_CONTATO ORDER BY NOME";

    /**
     * Cria o banco
     * @param context
     */
    public AgendaDao(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }//construtor

    /**
     * Responsável por GERAR A TABELA NO BANCO
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder query = new StringBuilder();
        query.append("CREATE TABLE TB_CONTATO(");
        query.append("ID INTEGER PRIMARY KEY AUTOINCREMENT,");
        query.append("NOME TEXT NOT NULL,");
        query.append("FONE TEXT NOT NULL,");
        query.append("EMAIL TEXT NOT NULL)");
        db.execSQL(query.toString());
    }//metodo

    /**
     * Responsável por ATUALIZAR A TABELA NO BANCO, quando necessário
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }//metodo

    public void inserir(Contato contato) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("NOME", contato.getNome());
        cv.put("FONE", contato.getFone());
        cv.put("EMAIL", contato.getEmail());
        db.insert("TB_CONTATO", null, cv);
    }//metodo

    public List<Contato> listar() {
        List<Contato> contatos = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(QUERY_SELECT, null);
        if (cursor.moveToFirst()) {
            do {
                Contato contato = new Contato();
                contato.setId(cursor.getLong(cursor.getColumnIndex("ID")));
                contato.setNome(cursor.getString(cursor.getColumnIndex("NOME")));
                contato.setFone(cursor.getString(cursor.getColumnIndex("FONE")));
                contato.setEmail(cursor.getString(cursor.getColumnIndex("EMAIL")));
                contatos.add(contato);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contatos;
    }//metodo

    public void excluir(long id) {
        SQLiteDatabase db = getReadableDatabase();
        String selection = "ID LIKE?";// Define 'where'
        String[] selectionArgs = {String.valueOf(id)};// Specify arguments in placeholder order.
        db.delete("TB_CONTATO", selection, selectionArgs);// Issue SQL statement.
    }//metodo

    public void update(Contato contato) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("NOME", contato.getNome());// COLUNA | VALOR
        values.put("FONE", contato.getFone());
        values.put("EMAIL", contato.getEmail());
        String selection = "ID LIKE?";
        String[] selectionArgs = {String.valueOf(contato.getId())};

        int count = db.update(
                "TB_CONTATO",
                values,
                selection,
                selectionArgs
        );
    }//metodo

}//classe
