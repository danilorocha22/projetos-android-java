package com.danilorocha.app.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.danilorocha.app.model.Contato;

import java.util.ArrayList;
import java.util.List;

public class ContatoDao {
    private static final String TB_CONTATO = "tb_contato";
    private static final String ID = "id";
    private static final String NOME = "nome";
    private static final String FONE = "fone";
    private static final String EMAIL = "email";
    private static final String QUERY_SELECT_LIST =
            "SELECT id, nome, fone, email FROM tb_contato ORDER BY nome";
    private final FabricaConexao conexao;


    public ContatoDao(FabricaConexao conexao) {
        this.conexao = conexao;
    }//construtor

    /**
     * Salva dos dados de um contato
     *
     * @param contato
     */
    public void salvarContato(Contato contato) {
        try (SQLiteDatabase db = conexao.conectar()) {
            ContentValues cv = new ContentValues();
            cv.put(NOME, contato.getNome());
            cv.put(FONE, contato.getTelefone());
            cv.put(EMAIL, contato.getEmail());
            db.insert(TB_CONTATO, null, cv);
        } catch (Exception e) {
            e.printStackTrace();
        }//catch
    }//metodo

    /**
     * Retorna uma lista com todos os contatos
     *
     * @return
     */
    public List<Contato> listarContatos() {
        List<Contato> contatos = new ArrayList<>();
        try (SQLiteDatabase db = conexao.conectar();
             Cursor cursor = db.rawQuery(QUERY_SELECT_LIST, null)) {
            if (cursor.moveToFirst()) {
                do {
                    Contato contato = new Contato();
                    contato.setId(cursor.getLong(cursor.getColumnIndex("id")));
                    contato.setNome(cursor.getString(cursor.getColumnIndex("nome")));
                    contato.setTelefone(cursor.getString(cursor.getColumnIndex("fone")));
                    contato.setEmail(cursor.getString(cursor.getColumnIndex("email")));
                    contatos.add(contato);
                } while (cursor.moveToNext());
            }//if
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return contatos;
        }//finally
    }//metodo

    /**
     * Deleta um contato pelo id
     *
     * @param id
     */
    public void excluirContato(long id) {
        SQLiteDatabase db = conexao.conectar();
        String selection = "id LIKE?";// Define 'where'
        String[] selectionArgs = {String.valueOf(id)};// Specify arguments in placeholder order.
        db.delete("tb_contato", selection, selectionArgs);// Issue SQL statement.
    }//metodo

    /**
     * Atualiza um contato
     *
     * @param contato
     */
    public void atualizarContato(Contato contato) {
        SQLiteDatabase db = conexao.conectar();
        ContentValues cv = new ContentValues();
        cv.put("nome", contato.getNome());
        cv.put("fone", contato.getTelefone());
        cv.put("email", contato.getEmail());
        String selection = "id LIKE?";
        String[] selectionArgs = {String.valueOf(contato.getId())};
        int linha = db.update(TB_CONTATO, cv, selection, selectionArgs);
    }//metodo

}//classe
