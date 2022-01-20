package com.danilo.signos;

import java.io.Serializable;

/**
 * Classe que representa um Signo na aplicação
 */
@SuppressWarnings("all")
public class Signo implements Serializable { //Serializable é necessário para trafegar o objeto de uma tela para outra
    private int diaInicio;
    private int diaFim;
    private int mesInicio;
    private int mesFim;
    private String nome;
    private String imagem;//caminho da imagem

    //Como a classe implementa Serializable é necessário um construtor padrão
    public Signo() {
    }

    //Construtor sobrecarregado
    public Signo(int diaInicio, int diaFim, int mesInicio, int mesFim, String nome, String imagem) {
        this.diaInicio = diaInicio;
        this.diaFim = diaFim;
        this.mesInicio = mesInicio;
        this.mesFim = mesFim;
        this.nome = nome;
        this.imagem = imagem;
    }

    //Somente getters devido estarmos usando a sobrecarga do construtor, pois já seta os dados como parâmetros
    public int getDiaInicio() {
        return diaInicio;
    }

    public int getDiaFim() {
        return diaFim;
    }

    public int getMesInicio() {
        return mesInicio;
    }

    public int getMesFim() {
        return mesFim;
    }

    public String getNome() {
        return nome;
    }

    public String getImagem() {
        return imagem;
    }
}
