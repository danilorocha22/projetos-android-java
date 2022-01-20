package com.danilorocha.nba.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.danilorocha.nba.entity.Jogo;
import com.danilorocha.nba.ui.timecasa.TimeCasa;

import java.util.ArrayList;
import java.util.List;

public class TimeCasaDao {
    private static final String TABELA = "tb_timeCasa";
    private static final String NOME_COMPLETO = "nomeCompleto";
    private static final String CIDADE = "cidade";
    private final FabricaConexao conexao;
    private static final String QUERY_SELECT_LIST =
            "SELECT nomeCompleto, cidade FROM tb_timeCasa";

    public TimeCasaDao(FabricaConexao conexao) {
        this.conexao = conexao;
    }

    public void salvarDados(List<Jogo> jogos) {
        if (obterDados().size() < Constantes.MAX) {
            try (SQLiteDatabase db = conexao.conectar()) {
                for (Jogo jogo : jogos) {
                    ContentValues cv = new ContentValues();
                    cv.put(NOME_COMPLETO, jogo.home_team.full_name);
                    cv.put(CIDADE, jogo.home_team.city);
                    db.insert(TABELA, null, cv);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//metodo

    public List<TimeCasa> obterDados() {
        try (SQLiteDatabase db = conexao.conectar();
             Cursor cursor = db.rawQuery(QUERY_SELECT_LIST, null);
        ) {
            List<TimeCasa> lista = new ArrayList<TimeCasa>();
            if (cursor.moveToFirst()) {
                do {
                    TimeCasa tc = new TimeCasa();
                    tc.setNomeCompleto(cursor.getString(cursor.getColumnIndex("nomeCompleto")));
                    tc.setCidade(cursor.getString(cursor.getColumnIndex("cidade")));
                    lista.add(tc);
                } while (cursor.moveToNext());
            }
            cursor.close();
            return lista;
        }
    }//metodo

}//classe
