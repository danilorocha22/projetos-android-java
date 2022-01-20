package com.danilorocha.nba.ui.vencedor;

import com.danilorocha.nba.entity.Jogo;

public class Vencedor {
    private Jogo jogo;
    private String nomeVencedor;

    public Vencedor() {
    }

    public Vencedor(Jogo jogo) {
        this.jogo = jogo;
    }

    public String obterVencedor(Jogo jogo) {
         return (jogo.home_team_score > jogo.visitor_team_score) ?
                 jogo.home_team.full_name : jogo.visitor_team.division;
    }

    public Jogo getJogo() {
        return jogo;
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }

    public String getNomeVencedor() {
        return nomeVencedor;
    }

    public void setNomeVencedor(String nomeVencedor) {
        this.nomeVencedor = nomeVencedor;
    }

    @Override
    public String toString() {
        return "\n"+nomeVencedor.toUpperCase()+"\n";
    }
}
