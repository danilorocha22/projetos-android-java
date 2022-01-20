package com.danilorocha.nba.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.danilorocha.nba.entity.Jogo;
import com.danilorocha.nba.ui.timefora.TimeFora;

import java.util.ArrayList;
import java.util.List;

public class TimeForaDao {
    private static final String TABELA = "tb_timeFora";
    private static final String NOME_COMPLETO = "nomeCompleto";
    private static final String CIDADE = "cidade";
    private final FabricaConexao conexao;
    private static final String QUERY_SELECT_LIST =
            "SELECT nomeCompleto, cidade FROM tb_timeFora";

    public TimeForaDao(FabricaConexao conexao) {
        this.conexao = conexao;
    }

    public void salvarDados(List<Jogo> jogos) {
        if (obterDados().size() < Constantes.MAX) {
            try (SQLiteDatabase db = conexao.conectar()) {
                for (Jogo jogo : jogos) {
                    ContentValues cv = new ContentValues();
                    cv.put(NOME_COMPLETO, jogo.visitor_team.full_name);
                    cv.put(CIDADE, jogo.visitor_team.city);
                    db.insert(TABELA, null, cv);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//metodo

    public List<TimeFora> obterDados() {
        try (SQLiteDatabase db = conexao.conectar();
             Cursor cursor = db.rawQuery(QUERY_SELECT_LIST, null);
        ) {
            List<TimeFora> lista = new ArrayList<TimeFora>();
            if (cursor.moveToFirst()) {
                do {
                    TimeFora tf = new TimeFora();
                    tf.setNomeCompleto(cursor.getString(cursor.getColumnIndex("nomeCompleto")));
                    tf.setCidade(cursor.getString(cursor.getColumnIndex("cidade")));
                    lista.add(tf);
                } while (cursor.moveToNext());
            }
            cursor.close();
            return lista;
        }
    }//metodo

}//classe
