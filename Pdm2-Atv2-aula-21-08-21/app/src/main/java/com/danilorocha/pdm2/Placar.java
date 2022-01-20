package com.danilorocha.pdm2;

public class Placar {
    private static int ponto;

    public static int getPonto() {
        return ponto;
    }

    public static void setPonto(int ponto) {
        Placar.ponto = ponto + getPonto();
    }
}
