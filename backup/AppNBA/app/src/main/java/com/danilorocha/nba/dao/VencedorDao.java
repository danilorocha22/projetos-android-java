package com.danilorocha.nba.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.danilorocha.nba.entity.Jogo;
import com.danilorocha.nba.ui.vencedor.Vencedor;

import java.util.ArrayList;
import java.util.List;

public class VencedorDao {
    private static final String TABELA = "tb_vencedor";
    private static final String NOME_VENCEDOR = "nomeVencedor";
    private final FabricaConexao conexao;
    private static final String QUERY_SELECT_LIST =
            "SELECT nomeVencedor FROM tb_vencedor";

    public VencedorDao(FabricaConexao conexao) {
        this.conexao = conexao;
    }

    public void salvarDados(List<Jogo> jogos) {
        if (obterDados().size() < Constantes.MAX) {
            try (SQLiteDatabase db = conexao.conectar()) {
                for (Jogo jogo : jogos) {
                    Vencedor timeVencedor = new Vencedor(jogo);
                    ContentValues cv = new ContentValues();
                    cv.put(NOME_VENCEDOR, timeVencedor.obterVencedor(jogo));
                    db.insert(TABELA, null, cv);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//metodo

    public List<Vencedor> obterDados() {
        try (SQLiteDatabase db = conexao.conectar();
             Cursor cursor = db.rawQuery(QUERY_SELECT_LIST, null);
        ) {
            List<Vencedor> lista = new ArrayList<Vencedor>();
            if (cursor.moveToFirst()) {
                do {
                    Vencedor v = new Vencedor();
                    v.setNomeVencedor(cursor.getString(cursor.getColumnIndex("nomeVencedor")));
                    lista.add(v);
                } while (cursor.moveToNext());
            }
            cursor.close();
            return lista;
        }
    }//metodo

}//classe
