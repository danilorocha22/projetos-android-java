package com.danilorocha.app.model;

public class Agenda {
    private static Integer tempo;
    private static String disciplina;

    public static String salvar(String disc, Integer hora, Integer minuto) {
        Integer t;
        if (disc.equals("Disciplinas:")) {
            return "Informe a disciplina";
        } else {
            if (hora.equals(0) && minuto.equals(0)) {
                return "Informe um tempo válido";
            } else {
                if (hora.equals(0))
                    t = minuto;
                else
                    t = (hora * 60) + minuto;
                disciplina = disc;
                tempo = t;
            }
        }
        return "Agendado com sucesso!";
    }//metodo

    //getters
    public static Integer getTempo() {
        return tempo;
    }

    public static String getDisciplina() {
        return disciplina;
    }

}//classe
