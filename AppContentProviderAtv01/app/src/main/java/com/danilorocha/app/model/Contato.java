package com.danilorocha.app.model;

public class Contato {
    private int id, type;
    private String data, mimType, photo_uri, account_t;

    public Contato() {
    }

    public Contato(int id, int type, String data, String mimType, String photo_uri, String account_t) {
        this.id = id;
        this.type = type;
        this.data = data;
        this.mimType = mimType;
        this.photo_uri = photo_uri;
        this.account_t = account_t;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMimType() {
        return mimType;
    }

    public void setMimType(String mimType) {
        this.mimType = mimType;
    }

    public String getPhoto_uri() {
        return photo_uri;
    }

    public void setPhoto_uri(String photo_uri) {
        this.photo_uri = photo_uri;
    }

    public String getAccount_t() {
        return account_t;
    }

    public void setAccount_t(String account_t) {
        this.account_t = account_t;
    }

    @Override
    public String toString() {
        String contato = "Contato: " + "id: " + id +"; ";
        String recurso = (type == 1) ? "e-mail: " : "celular: ";
        contato += recurso + data;
        return contato;
    }

}//classe
