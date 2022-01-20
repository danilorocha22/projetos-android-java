package com.danilorocha.app.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataHelper extends SQLiteOpenHelper {
    private static final String CREATE_TB_CONTATO =
            "CREATE TABLE IF NOT EXISTS [tb_contato] (" +
                    "[id] INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "[nome] TEXT NOT NULL," +
                    "[fone] TEXT NOT NULL," +
                    "[email] TEXT NOT NULL)";

    public DataHelper(Context context,
                      String banco,
                      SQLiteDatabase.CursorFactory factory,
                      int versao) {

        super(context, banco, factory, versao);
    }//construtor

    /**
     * Criar tabela Contato
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TB_CONTATO);
    }//metodo

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}//metodo

}//classe
