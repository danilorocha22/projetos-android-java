package com.danilorocha.nba.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.danilorocha.nba.entity.Jogo;
import com.danilorocha.nba.ui.descricao.Descricao;
import com.danilorocha.nba.ui.resultado.Resultado;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ResultadoDao {
    private static final String TABELA = "tb_resultado";
    private static final String PONTOS_TIME_CASA = "pontosTimeCasa";
    private static final String NOME_TIME_CASA = "nomeTimeCasa";
    private static final String PONTOS_TIME_FORA = "pontosTimeFora";
    private static final String NOME_TIME_FORA = "nomeTimeFora";
    private final FabricaConexao conexao;
    private static final String QUERY_SELECT_LIST =
            "SELECT pontosTimeCasa, nomeTimeCasa, pontosTimeFora, nomeTimeFora FROM tb_resultado";

    public ResultadoDao(FabricaConexao conexao) {
        this.conexao = conexao;
    }

    public void salvarDados(List<Jogo> jogos) {
        if (obterDados().size() < Constantes.MAX) {
            try (SQLiteDatabase db = conexao.conectar()) {
                for (Jogo jogo : jogos) {
                    ContentValues cv = new ContentValues();
                    cv.put(PONTOS_TIME_CASA, jogo.home_team_score);
                    cv.put(NOME_TIME_CASA, jogo.home_team.name);
                    cv.put(PONTOS_TIME_FORA, jogo.visitor_team_score);
                    cv.put(NOME_TIME_FORA, jogo.visitor_team.name);
                    db.insert(TABELA, null, cv);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//metodo

    public List<Resultado> obterDados() {
        try (SQLiteDatabase db = conexao.conectar();
             Cursor cursor = db.rawQuery(QUERY_SELECT_LIST, null);
        ) {
            List<Resultado> lista = new ArrayList<Resultado>();
            if (cursor.moveToFirst()) {
                do {
                    Resultado r = new Resultado();
                    r.setPontosTimeCasa(cursor.getInt(cursor.getColumnIndex("pontosTimeCasa")));
                    r.setNomeTimeCasa(cursor.getString(cursor.getColumnIndex("nomeTimeCasa")));
                    r.setPontosTimeFora(cursor.getInt(cursor.getColumnIndex("pontosTimeFora")));
                    r.setNomeTimeFora(cursor.getString(cursor.getColumnIndex("nomeTimeFora")));
                    lista.add(r);
                } while (cursor.moveToNext());
            }
            cursor.close();
            return lista;
        }
    }//metodo

}//classe
