package com.danilorocha.app.model;

import java.util.ArrayList;
import java.util.List;

public class Matematica extends Disciplina {
    private final String QUESTAO_1 = "1ª) Qual número aumenta seu valor em 50% quando posto de cabeça para baixo?";
    private final String QUESTAO_2 = "2ª) Dois pais e dois filhos sentam para tomar café. Cada um comeu um ovo, quantos eles comeram no total?";
    private final String QUESTAO_3 = "3ª) Se meu avô tem quatro filhos e cada filho por sua vez também tem quatro filhos, quantos primos eu tenho?";

    private final List<String> ALTERNATIVAS_QUESTAO_1 = new ArrayList<String>() {{
       add("3"); add("6"); add("9"); add("8");
    }};//lista

    private final List<String> ALTERNATIVAS_QUESTAO_2 = new ArrayList<String>() {{
           add("2"); add("4"); add("3"); add("5");
    }};//lista

    private final List<String> ALTERNATIVAS_QUESTAO_3 = new ArrayList<String>() {{
               add("13"); add("5"); add("19"); add("12");
    }};//lista

    @Override
    public boolean avaliarQuestao1(String res) {
         return (res == "6") ? true : false;
    }

    @Override
    public boolean avaliarQuestao2(String res) {
        return (res == "3") ? true : false;
    }

    @Override
    public boolean avaliarQuestao3(String res) {
        return (res == "12") ? true : false;
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