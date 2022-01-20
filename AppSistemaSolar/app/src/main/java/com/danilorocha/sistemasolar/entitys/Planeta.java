package com.danilorocha.sistemasolar.entitys;

public class Planeta {
    private String nome;
    private String descricao;
    private int imagemId;

    public Planeta(String nome, String descricao, int imagemId) {
        this.nome = nome;
        this.descricao = descricao;
        this.imagemId = imagemId;
    }//construtor sobrecarregado

    //getters
    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getImagemId() {
        return imagemId;
    }

}//classe
