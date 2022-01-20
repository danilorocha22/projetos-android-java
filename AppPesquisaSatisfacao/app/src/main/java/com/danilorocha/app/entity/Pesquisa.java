package com.danilorocha.app.entity;

import java.io.Serializable;

public class Pesquisa implements Serializable {
    private String usuario, cidade, resposta;

    public Pesquisa(String usuario, String cidade, String resposta) {
        this.usuario = usuario;
        this.cidade = cidade;
        this.resposta = resposta;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getCidade() {
        return cidade;
    }

    public String getResposta() {
        return resposta;
    }

    @Override
    public String toString() {
        return "Usuário: "+ usuario +
                "\nCidade: "+ cidade +
                "\nResposta: "+ resposta;
    }

}
