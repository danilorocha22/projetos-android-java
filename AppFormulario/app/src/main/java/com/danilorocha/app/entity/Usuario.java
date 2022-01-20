package com.danilorocha.app.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Usuario implements Serializable {
    private String nome;
    private int idade;
    private String sexo;
    private List<String> estilosMusicais;
    public static List<Usuario> listaUsuarios = new ArrayList<>();

    public Usuario(String nome, int idade, String sexo) {
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
        this.estilosMusicais = new ArrayList<String>();
    }//construtores

    //getters e setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public List<String> getEstilosMusicais() {
        return estilosMusicais;
    }

    public void setEstilosMusicais(List<String> estilosMusicais) {
        this.estilosMusicais = estilosMusicais;
    }

    @Override
    public String toString() {
        String user = "\nNome: "+nome +";"+
                      "\nIdade: "+ idade +";"+
                      "\nSexo: "+ sexo +";"+
                      "\nEstilos musicais preferidos: "+"\n";
        for (String s : estilosMusicais) {
            user += s+" ";
        }
        return user+"\n";
    }//toString

}//classe
