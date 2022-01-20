package com.danilorocha.app.model;

import java.util.ArrayList;
import java.util.List;

public class Ciencias extends Disciplina {
    private final String QUESTAO_1 = "1ª) Atualmente, quantos elementos químicos a tabela periódica possui?";
    private final String QUESTAO_2 = "2ª) De quem é a famosa frase \"Penso, logo existo\"?";
    private final String QUESTAO_3 = "3ª) De onde é a invenção do chuveiro elétrico?";

    private final List<String> ALTERNATIVAS_QUESTAO_1 = new ArrayList<String>() {{
        add("190"); add("113"); add("120"); add("118");
    }};//lista

    private final List<String> ALTERNATIVAS_QUESTAO_2 = new ArrayList<String>() {{
        add("Platão"); add("Galileu Galilei"); add("Descartes"); add("Sócrates");
    }};//lista

    private final List<String> ALTERNATIVAS_QUESTAO_3 = new ArrayList<String>() {{
        add("Brasil"); add("Inglaterra"); add("EUA"); add("França");
    }};//lista

    @Override
    public boolean avaliarQuestao1(String res) {
        return (res == "118") ? true : false;
    }

    @Override
    public boolean avaliarQuestao2(String res) {
        return (res == "Descartes") ? true : false;
    }

    @Override
    public boolean avaliarQuestao3(String res) {
        return (res == "Brasil") ? true : false;
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