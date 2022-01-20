package com.danilorocha.nba.ui.resultado;

import com.danilorocha.nba.ui.vencedor.Vencedor;

public class Resultado {
    private int pontosTimeCasa, pontosTimeFora;
    private String nomeTimeCasa, nomeTimeFora;

    public Resultado() {
    }

    public Resultado(int pontosTimeCasa, int pontosTimeFora, String nomeTimeCasa, String nomeTimeFora) {
        this.pontosTimeCasa = pontosTimeCasa;
        this.pontosTimeFora = pontosTimeFora;
        this.nomeTimeCasa = nomeTimeCasa;
        this.nomeTimeFora = nomeTimeFora;
    }

    public int getPontosTimeCasa() {
        return pontosTimeCasa;
    }

    public void setPontosTimeCasa(int pontosTimeCasa) {
        this.pontosTimeCasa = pontosTimeCasa;
    }

    public int getPontosTimeFora() {
        return pontosTimeFora;
    }

    public void setPontosTimeFora(int pontosTimeFora) {
        this.pontosTimeFora = pontosTimeFora;
    }

    public String getNomeTimeCasa() {
        return nomeTimeCasa;
    }

    public void setNomeTimeCasa(String nomeTimeCasa) {
        this.nomeTimeCasa = nomeTimeCasa;
    }

    public String getNomeTimeFora() {
        return nomeTimeFora;
    }

    public void setNomeTimeFora(String nomeTimeFora) {
        this.nomeTimeFora = nomeTimeFora;
    }

    @Override
    public String toString() {
        return  "\n"+nomeTimeCasa.toUpperCase() +" "+
                pontosTimeCasa +" x "+ pontosTimeFora +" "+
                nomeTimeFora.toUpperCase()+"\n";
    }
}
