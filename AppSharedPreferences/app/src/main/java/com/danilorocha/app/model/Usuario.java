package com.danilorocha.app.model;

import java.io.Serializable;

public class Usuario implements Serializable {
    private String login;
    private String senha;

    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }//construtor

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

}//classe
