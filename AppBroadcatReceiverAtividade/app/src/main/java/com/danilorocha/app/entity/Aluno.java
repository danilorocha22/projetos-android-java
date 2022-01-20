package com.danilorocha.app.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Aluno implements Serializable {
    private String nome;
    private int idade;
    private double mediaNotas;
    private List<Double> notas;

    public Aluno(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
        this.notas = new ArrayList<Double>();
    }//construtor sobrecarregado

    //getters e setters
    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public double getMediaNotas() {
        return mediaNotas;
    }

    public void setMediaNotas(double mediaNotas) {
        this.mediaNotas = mediaNotas;
    }

    public List<Double> getNotas() {
        return notas;
    }

    public void setNotas(Double nota) {
        this.notas.add(nota);
    }

    @Override
    public String toString() {
        String aluno = "\nAluno: "+ nome +
                        "\nIdade: " + idade +
                        "\nNotas: ";
        for (int i = 0; i < notas.size(); i++) {
            aluno += "\n            "+ (1+i) + "º B: "+ notas.get(i);
        }
        aluno += "\nMédia Total: "+ Secretaria.arredondar(mediaNotas);
        return aluno +"\n";
    }//toString

}//classe
