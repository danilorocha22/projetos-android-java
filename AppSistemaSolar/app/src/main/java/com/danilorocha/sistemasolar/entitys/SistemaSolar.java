package com.danilorocha.sistemasolar.entitys;

import com.danilorocha.sistemasolar.R;

import java.util.ArrayList;
import java.util.List;

public final class SistemaSolar {
    private static final List<Planeta> PLANETAS = new ArrayList<Planeta>() {{
        add(new Planeta("Sol", getDescricao("sol"), R.drawable.sol));
        add(new Planeta("Mercúrio", getDescricao("mercurio"), R.drawable.mercurio));
        add(new Planeta("Vênus", getDescricao("venus"), R.drawable.venus));
        add(new Planeta("Terra", getDescricao("terra"), R.drawable.terra));
        add(new Planeta("Marte", getDescricao("marte"),R.drawable.marte));
        add(new Planeta("Júpiter", getDescricao("jupiter"), R.drawable.jupiter));
        add(new Planeta("Saturno", getDescricao("saturno"), R.drawable.saturno));
        add(new Planeta("Urano", getDescricao("urano"), R.drawable.urano));
        add(new Planeta("Netuno", getDescricao("netuno"), R.drawable.netuno));
    }};//inicialização com chaves dupla

    private static final String getDescricao(String planeta) {
        switch (planeta) {
            case "sol": return "O Sol é a estrela central do Sistema Solar e os planetas giram ao seu redor.";
            case "mercurio": return "Mercúrio é o menor e mais interno planeta do Sistema Solar.";
            case "venus": return "Vênus é o segundo planeta do Sistema Solar em ordem de distância a partir do Sol.";
            case "terra": return "A Terra é o terceiro planeta mais próximo do Sol, o mais denso e o quinto maior.";
            case "marte": return "Marte é o quarto planeta a partir do Sol, o segundo menor do Sistema Solar.";
            case "jupiter": return "Júpiter é o maior planeta do Sistema Solar e é o quinto mais próximo do Sol.";
            case "saturno": return "Saturno é o sexto planeta a partir do Sol e o segundo maior do Sistema Solar.";
            case "urano": return "Urano é o sétimo planeta a partir do Sol, o terceiro maior e o quarto mais massivo.";
            default: return "Netuno é o último planeta do Sistema Solar, desde a reclassificação de Plutão para planeta anão.";
        }
    }//metodo

    public static final List<Planeta> planetas() {
        return PLANETAS;
    }//retorna a lista de planetas

}//classe
