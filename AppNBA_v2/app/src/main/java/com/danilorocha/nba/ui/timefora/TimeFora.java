package com.danilorocha.nba.ui.timefora;

public class TimeFora {
    private String nomeCompleto;
    private String cidade;

    public TimeFora() {
    }

    public TimeFora(String nomeCompleto, String cidade) {
        this.nomeCompleto = nomeCompleto;
        this.cidade = cidade;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    @Override
    public String toString() {
        return "\n"+nomeCompleto.toUpperCase() +"\n"+
               "cidade "+ cidade+"\n";
    }

}//classe
