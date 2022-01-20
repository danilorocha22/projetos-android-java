package com.danilorocha.app.model;

import java.util.ArrayList;
import java.util.List;

public class Portugues extends Disciplina {
    private final String QUESTAO_1 = "1ª) A palavra \"volúpia\" está relacionada com?";
    private final String QUESTAO_2 = "2ª) Soteropolitano é o gentílico para a capital de qual estado brasileiro?";
    private final String QUESTAO_3 = "3ª) Notívago é aquele que...";

    private final List<String> ALTERNATIVAS_QUESTAO_1 = new ArrayList<String>() {{
        add("nervosismo"); add("pobreza"); add("luxúria"); add("doença");
    }};//lista

    private final List<String> ALTERNATIVAS_QUESTAO_2 = new ArrayList<String>() {{
        add("Bahia"); add("Ceará"); add("Santa Catarina"); add("Mato Grosso do Sul");
    }};//lista

    private final List<String> ALTERNATIVAS_QUESTAO_3 = new ArrayList<String>() {{
        add("faz anotações"); add("dá notas para os alunos"); add("monta automóveis"); add("tem hábitos noturnos");
    }};//lista

    @Override
    public boolean avaliarQuestao1(String res) {
        return (res == "luxúria") ? true : false;
    }

    @Override
    public boolean avaliarQuestao2(String res) {
        return (res == "Bahia") ? true : false;
    }

    @Override
    public boolean avaliarQuestao3(String res) {
        return (res == "tem hábitos noturnos") ? true : false;
    }

    @Override
    public String getQUESTAO_1() {
        return QUESTAO_1;
    }

    @Override
    public String getQUESTAO_2() {
        return QUESTAO_2;
    }

    @Override
    public String getQUESTAO_3() {
        return QUESTAO_3;
    }

    @Override
    public List<String> getALTERNATIVAS_QUESTAO_1() {
        return ALTERNATIVAS_QUESTAO_1;
    }

    @Override
    public List<String> getALTERNATIVAS_QUESTAO_2() {
        return ALTERNATIVAS_QUESTAO_2;
    }

    @Override
    public List<String> getALTERNATIVAS_QUESTAO_3() {
        return ALTERNATIVAS_QUESTAO_3;
    }

}//classe