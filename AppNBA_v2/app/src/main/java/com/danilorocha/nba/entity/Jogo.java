package com.danilorocha.nba.entity;

import java.util.ArrayList;
import java.util.List;

public class Jogo {
    public int id;
    public String date;
    public TimeAnfitriao home_team;
    public int home_team_score;
    public int period;
    public boolean postseason;
    public int season;
    public String status;
    public String time;
    public TimeVisitante visitor_team;
    public int visitor_team_score;
    private List<Jogo> listaJogos = new ArrayList<Jogo>();

    public void setListaJogos(Jogo jogo) {
        this.listaJogos.add(jogo);
    }

    public List<Jogo> getListaJogos() {
        return listaJogos;
    }
}//classe
