package com.danilorocha.nba.dao;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.danilorocha.nba.dao.DescricaoDao;
import com.danilorocha.nba.dao.FabricaConexao;

/**
 * A regra de negócio do App NBA é apenas a disponibilização dos dados de 20 jogos aleatórios,
 * não havendo necessidade de implmentação de metodos de inserção, delete ou update.
 * Com este provedor de conteúdo está sendo disponibilizada a tabela de descrições de 20 jogos da NBA.
 */
public class DescricaoJogosProvider extends ContentProvider {
    private DescricaoDao descricaoDao;
    private FabricaConexao conexao;
    public static final String AUTHORITY = "com.danilorocha.nba.appnba.provider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/tb_descricao");
    private static final int DESCRICOES = 1;
    private static UriMatcher uriDescricoes;

    static {
        uriDescricoes = new UriMatcher(UriMatcher.NO_MATCH);//Para acessar app através da URI personalizada
        uriDescricoes.addURI(AUTHORITY, "tb_descricao/", DESCRICOES);
    }

    @Override
    public boolean onCreate() {
        conexao = new FabricaConexao(getContext());
        descricaoDao = new DescricaoDao(conexao);
        return true;
    }//onCreate

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        Cursor cursor;
        switch (uriDescricoes.match(uri)) {
            case DESCRICOES:
                cursor = descricaoDao.query();
                break;

            default:
                throw new IllegalArgumentException("Uri inválida " + uri);
        }//switch
        return cursor;
    }//metodo

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriDescricoes.match(uri)) {
            case DESCRICOES:
                return ("vnd.android.cursor.dir/vnd.danilorocha.nba.descricoes");
            default:
                throw new IllegalArgumentException("Uri inválida " + uri);
        }//switch
    }//metodo

    /**
     * Não implementado pois não faz parte da regra de negócio do App NBA.
     * @param uri
     * @param values
     * @return
     */
    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    /**
     * Não implementado pois não faz parte da regra de negócio do App NBA.
     * @param uri
     * @param selection
     * @param selectionArgs
     * @return
     */
    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    /**
     * Não implementado pois não faz parte da regra de negócio do App NBA.
     * @param uri
     * @param values
     * @param selection
     * @param selectionArgs
     * @return
     */
    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection,
                      @Nullable String[] selectionArgs) {
        return 0;
    }

}//classe
