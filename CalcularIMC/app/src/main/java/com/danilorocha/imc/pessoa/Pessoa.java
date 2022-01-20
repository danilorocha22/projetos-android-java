package com.danilorocha.imc.pessoa;

import java.io.Serializable;
import java.text.DecimalFormat;

public class Pessoa implements Serializable {
    private String nome;
    private float peso;
    private float altura;
    private double imc;

    public Pessoa(String nome, float peso, float altura) {
        this.nome = nome;
        this.peso = peso;
        this.altura = altura;
    }

    public String getNome() {
        return nome;
    }

    public float getPeso() {
        return peso;
    }

    public float getAltura() {
        return altura;
    }

    public double getImc() {
        return imc;
    }

    public void setImc() {
        this.imc = peso / (altura * altura);
    }

    public String formatarValor(double valor) {
        return new DecimalFormat("0.00").format(valor);
    }

}
