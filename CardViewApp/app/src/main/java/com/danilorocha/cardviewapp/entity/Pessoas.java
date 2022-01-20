package com.danilorocha.cardviewapp.entity;

import java.util.ArrayList;
import java.util.List;

public class Pessoas {
    public static final List<Pessoa> lista = new ArrayList<Pessoa>() {{
        add(new Pessoa("Danilo Rocha", 35, qualificacao("Danilo Rocha")));
        add(new Pessoa("João Pedro", 40, qualificacao("João Pedro")));
        add(new Pessoa("Carlos Eduardo", 22, qualificacao("Carlos Eduardo")));
        add(new Pessoa("Maria Rita", 25, qualificacao("Maria Rita")));
        add(new Pessoa("Júlia Araújo", 45, qualificacao("Júlia Araújo")));
        add(new Pessoa("Mariana Araújo", 30, qualificacao("Mariana Araújo")));
    }};

    private static final String qualificacao(String nome) {
        switch (nome) {
            case "Danilo Rocha": return "- Loren Ipsum ortum;\n- Cito ducunt ad primus;\n- Lunas cantare.";
            case "João Pedro": return "- Cum bromium potus;\n- Omnes menses amor;\n- Barbatus, flavum.";
            case "Carlos Eduardo": return "- Pol Ubi est noster;\n- Adelphis est;\n- Avarius demissio.";
            case "Maria Rita": return "- Est dexter ausus;\n- Cesaris accelerare;\n- Una ducunt ad.";
            case "Júlia Araújo": return "- Ubi est gratis xiphias;\n- Cobaltum, mensa, et fluctus.";
            default: return "- Cum hydra volare;\n- Omnes sagaes talem.";
        }
    }//metodo
}
