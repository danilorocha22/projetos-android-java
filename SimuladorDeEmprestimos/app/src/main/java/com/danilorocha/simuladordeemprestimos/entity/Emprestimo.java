package com.danilorocha.simuladordeemprestimos.entity;

import java.util.ArrayList;

public class Emprestimo {
    private final String AUTORIZADO = "Empréstimo autorizado";
    private final String NEGADO = "Empréstimo negado (parcela superior a 30% do salário)";

    public String verificar(float salario, float parcela) {
        return (parcela <= salario * 0.3) ? AUTORIZADO : NEGADO;
    }//metodo

    public float calcularJuros(float capital, int prazo) {
        switch (prazo) {
            case 1: return capital * 6 * 0.015F;
            case 2: return capital * 12 * 0.015F;
            case 3: return capital * 18 * 0.02F;
            case 4: return capital * 24 * 0.022F;
            case 5: return capital * 30 * 0.025F;
            case 6: return capital * 36 * 0.025F;
            default: return capital * 42 * 0.03F;
        }
    }//metodo

    public int prazo(int opcao) {
        switch (opcao) {
            case 1: return 6;
            case 2: return 12;
            case 3: return 18;
            case 4: return 24;
            case 5: return 30;
            case 6: return 36;
            default: return 42;
        }
    }//metodo

}//classe
