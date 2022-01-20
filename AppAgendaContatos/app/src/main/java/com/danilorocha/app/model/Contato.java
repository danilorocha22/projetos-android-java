package com.danilorocha.app.model;

public class Contato {
    private long id;
    private String nome, fone, email;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return nome.toUpperCase() +
                "\nTelefone: " + fone +
                "\nE-mail: " + email;
    }

}
