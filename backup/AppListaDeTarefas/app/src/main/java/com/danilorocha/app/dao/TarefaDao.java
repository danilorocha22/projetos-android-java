package com.danilorocha.app.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.danilorocha.app.model.Tarefa;

import java.util.ArrayList;
import java.util.List;

public class TarefaDao {
    private static final String TB_TAREFA = "TB_TAREFA";
    private static final String ID = "ID";
    private static final String TITULO = "TITULO";
    private static final String DESCRICAO = "DESCRICAO";
    private static final String DATA = "DATA";
    private final FabricaConexao conexao;
    private static final String QUERY_SELECT_LIST =
            "SELECT ID, TITULO, DESCRICAO, DATA FROM TB_TAREFA ORDER BY DATA";


    public TarefaDao(FabricaConexao conexao) {
        this.conexao = conexao;
    }//construtor

    /**
     * Salva os dados de um tarefa
     *
     * @param tarefa
     */
    public void salvarTarefa(Tarefa tarefa) {
        try (SQLiteDatabase db = conexao.conectar()) {
            ContentValues cv = new ContentValues();
            cv.put(TITULO, tarefa.getTitulo());
            cv.put(DESCRICAO, tarefa.getDescricao());
            cv.put(DATA, String.valueOf(tarefa.getData()));
            db.insert(TB_TAREFA, null, cv);
        } catch (Exception e) {
            e.printStackTrace();
        }//catch
    }//metodo

    /**
     * Retorna uma lista com todas as tarefas
     *
     * @return
     */
    public List<Tarefa> listarTarefas() {
        List<Tarefa> tarefas = new ArrayList<>();
        try (SQLiteDatabase db = conexao.conectar();
             Cursor cursor = db.rawQuery(QUERY_SELECT_LIST, null)) {
            if (cursor.moveToFirst()) {
                do {
                    Tarefa tarefa = new Tarefa();
                    tarefa.setId(cursor.getInt(cursor.getColumnIndex("ID")));
                    tarefa.setTitulo(cursor.getString(cursor.getColumnIndex("TITULO")));
                    tarefa.setDescricao(cursor.getString(cursor.getColumnIndex("DESCRICAO")));
                    tarefa.setData(tarefa.formatarData(cursor.getString(cursor.getColumnIndex("DATA"))));
                    tarefas.add(tarefa);
                } while (cursor.moveToNext());
            }//if
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return tarefas;
        }//finally
    }//metodo

    /**
     * Deleta uma tarefa pelo id
     *
     * @param id
     */
    public void excluirTarefa(long id) {
        SQLiteDatabase db = conexao.conectar();
        String selection = "id LIKE?";// Define 'where'
        String[] selectionArgs = {String.valueOf(id)};// Specify arguments in placeholder order.
        db.delete("TB_TAREFA", selection, selectionArgs);// Issue SQL statement.
    }//metodo

    /**
     * Atualiza uma tarefa
     *
     * @param tarefa
     */
    public void atualizarTarefa(Tarefa tarefa) {
        SQLiteDatabase db = conexao.conectar();
        ContentValues cv = new ContentValues();
        cv.put("TITULO", tarefa.getTitulo());
        cv.put("DESCRICAO", tarefa.getDescricao());
        cv.put("DATA", String.valueOf(tarefa.getData()));
        String selection = "id LIKE?";
        String[] selectionArgs = {String.valueOf(tarefa.getId())};
        int linha = db.update(TB_TAREFA, cv, selection, selectionArgs);
    }//metodo

}//classe
