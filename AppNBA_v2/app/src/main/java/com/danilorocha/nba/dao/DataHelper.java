package com.danilorocha.nba.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataHelper extends SQLiteOpenHelper {

    private static final String CREATE_TB_DESCRICAO =
            "CREATE TABLE IF NOT EXISTS [tb_descricao] (" +
            "[id] INTEGER PRIMARY KEY NOT NULL, " +
            "[data] TEXT NOT NULL, " +
            "[siglaTimeCasa] TEXT NOT NULL, " +
            "[siglaTimeFora] TEXT NOT NULL)";

    private static final String CREATE_TB_TIME_CASA =
            "CREATE TABLE IF NOT EXISTS [tb_timeCasa] (" +
            "[nomeCompleto] TEXT NOT NULL, " +
            "[cidade] TEXT NOT NULL)";

    private static final String CREATE_TB_TIME_FORA =
            "CREATE TABLE IF NOT EXISTS [tb_timeFora] (" +
            "[nomeCompleto] TEXT NOT NULL, " +
            "[cidade] TEXT NOT NULL)";

    private static final String CREATE_TB_RESULTADO =
            "CREATE TABLE IF NOT EXISTS [tb_resultado] (" +
            "[pontosTimeCasa] INTEGER NOT NULL, " +
            "[nomeTimeCasa] TEXT NOT NULL, " +
            "[pontosTimeFora] INTEGER NOT NULL, " +
            "[nomeTimeFora] TEXT NOT NULL)";

    private static final String CREATE_TB_VENCEDOR =
            "CREATE TABLE IF NOT EXISTS [tb_vencedor] (" +
            "[nomeVencedor] TEXT NOT NULL)";


    public DataHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TB_DESCRICAO);
        db.execSQL(CREATE_TB_TIME_CASA);
        db.execSQL(CREATE_TB_TIME_FORA);
        db.execSQL(CREATE_TB_RESULTADO);
        db.execSQL(CREATE_TB_VENCEDOR);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

}//classe
