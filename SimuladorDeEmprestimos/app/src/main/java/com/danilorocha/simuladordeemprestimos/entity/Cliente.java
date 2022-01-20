package com.danilorocha.simuladordeemprestimos.entity;

public class Cliente {
    private String nome;
    private float salario, parcela, valorEmprestimo;
    private Emprestimo emprestimo;

    public Cliente(String nome, float salario, float valorEmprestimo) {
        this.nome = nome;
        this.salario = salario;
        this.valorEmprestimo = valorEmprestimo;
        this.emprestimo = new Emprestimo();
    }//construtor

    //getts e setts
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public float getParcela() {
        return parcela;
    }

    public void setParcela(float parcela) {
        this.parcela = parcela;
    }

    public float getValorEmprestimo() {
        return valorEmprestimo;
    }

    public void setValorEmprestimo(float valorEmprestimo) {
        this.valorEmprestimo = valorEmprestimo;
    }

    public Emprestimo getEmprestimo() {
        return emprestimo;
    }

    @Override
    public String toString() {
        return "Cliente: " + nome +
               "\nSalário: R$ " + salario +
               "\nEmpréstimo: R$ "+ valorEmprestimo +
               "\nParcela: R$ " + parcela +
               "\n\nResultado: " + emprestimo.verificar(salario, parcela);
    }//toString

}//classe
