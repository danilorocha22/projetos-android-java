package com.danilorocha.app.model;

import java.util.List;

public abstract class Disciplina {

    private static Integer pontos = 0;

    public String pontuar(int questao, String resposta) {
        switch (questao) {
            case 1:
                if (avaliarQuestao1(resposta)) {
                    pontos += 1;
                    return "Resposta certa";
                }
                break;

            case 2:
                if (avaliarQuestao2(resposta)) {
                    pontos += 1;
                    return "Resposta certa";
                }
                break;

            case 3:
                if (avaliarQuestao3(resposta)) {
                    pontos += 1;
                    return "Resposta certa";
                }
                break;
        }//switch
        return "Resposta errada";
    }//metodo

    public static Integer getPontos() {
        return pontos;
    }

    public static void setPontos(Integer pontos) {
        Disciplina.pontos = pontos;
    }

    public abstract boolean avaliarQuestao1(String resposta);
    public abstract boolean avaliarQuestao2(String resposta);
    public abstract boolean avaliarQuestao3(String resposta);
    public abstract String getQUESTAO_1();
    public abstract String getQUESTAO_2();
    public abstract String getQUESTAO_3();
    public abstract List<String> getALTERNATIVAS_QUESTAO_1();
    public abstract List<String> getALTERNATIVAS_QUESTAO_2();
    public abstract List<String> getALTERNATIVAS_QUESTAO_3();
}//classe
