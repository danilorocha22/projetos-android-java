package com.danilorocha.nba.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.danilorocha.nba.entity.Jogo;
import com.danilorocha.nba.ui.descricao.Descricao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DescricaoDao {
    private static final String TABELA = "tb_descricao";
    private static final String ID = "id";
    private static final String DATA = "data";
    private static final String SIGLA_TIME_CASA = "siglaTimeCasa";
    private static final String SIGLA_TIME_FORA = "siglaTimeFora";
    private final FabricaConexao conexao;
    private static final String QUERY_SELECT_LIST =
            "SELECT id, data, siglaTimeCasa, siglaTimeFora FROM tb_descricao ORDER BY id";

    public DescricaoDao(FabricaConexao conexao) {
        this.conexao = conexao;
    }

    public void salvarDados(List<Jogo> jogos) {
        if (obterDados().size() < Constantes.MAX) {
            try (SQLiteDatabase db = conexao.conectar()) {
                for (Jogo jogo : jogos) {
                    Descricao d = new Descricao();
                    ContentValues cv = new ContentValues();
                    cv.put(ID, jogo.id);
                    cv.put(DATA, d.formatarData(jogo.date));
                    cv.put(SIGLA_TIME_CASA, jogo.home_team.abbreviation);
                    cv.put(SIGLA_TIME_FORA, jogo.visitor_team.abbreviation);
                    db.insert(TABELA, null, cv);
                }
                System.out.println("Dados inseridos com sucesso");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Não foi possível inserir no banco");
        }
    }//metodo

    public List<Descricao> obterDados() {
        try (SQLiteDatabase db = conexao.conectar();
             Cursor cursor = db.rawQuery(QUERY_SELECT_LIST, null);
        ) {
            List<Descricao> lista = new ArrayList<Descricao>();
            if (cursor.moveToFirst()) {
                do {
                    Descricao d = new Descricao();
                    d.setId(cursor.getInt(cursor.getColumnIndex("id")));
                    d.setData(cursor.getString(cursor.getColumnIndex("data")));
                    d.setSiglaTimeCasa(cursor.getString(cursor.getColumnIndex("siglaTimeCasa")));
                    d.setSiglaTimeFora(cursor.getString(cursor.getColumnIndex("siglaTimeFora")));
                    lista.add(d);
                } while (cursor.moveToNext());
            }
            cursor.close();
            return lista;
        }
    }//metodo

}//classe
