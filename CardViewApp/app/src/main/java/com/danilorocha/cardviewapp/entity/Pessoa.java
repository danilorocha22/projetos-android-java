package com.danilorocha.cardviewapp.entity;

import java.util.List;

public class Pessoa {
    private String nome, qualificacao;
    private int idade;

    public Pessoa(String nome, int idade, String qualificacao) {
        this.nome = nome;
        this.idade = idade;
        this.qualificacao = qualificacao;
    }//construtor

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getQualificacao() {
        return qualificacao;
    }

    public void setQualificacao(String qualificacao) {
        this.qualificacao = qualificacao;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    @Override
    public String toString() {
        return "Nome: "+ nome +
               "\nIdade: "+ idade;
    }
}
