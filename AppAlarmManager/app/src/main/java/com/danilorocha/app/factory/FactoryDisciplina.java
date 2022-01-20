package com.danilorocha.app.factory;

import com.danilorocha.app.model.Ciencias;
import com.danilorocha.app.model.Disciplina;
import com.danilorocha.app.model.Matematica;
import com.danilorocha.app.model.Portugues;

public interface FactoryDisciplina {

    static Disciplina newInstance(String nome) {
        switch (nome) {
            case "Matemática": return new Matematica();
            case "Português": return new Portugues();
            case "Ciências": return new Ciencias();
            default: throw new IllegalArgumentException("Disciplina inexistente");
        }
    }//metodo

}//classe
