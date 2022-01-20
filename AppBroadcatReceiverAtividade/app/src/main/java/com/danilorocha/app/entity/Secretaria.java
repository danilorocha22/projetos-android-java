package com.danilorocha.app.entity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Secretaria {
    public static List<Aluno> aprovados = new ArrayList<>();
    public static List<Aluno> reprovados = new ArrayList<>();

    public static void cadastrar(Aluno aluno) {
        if (verificarAprovacao(aluno))
            aprovados.add(aluno);
        else
            reprovados.add(aluno);
    }//salva na lista

    public static boolean verificarAprovacao(Aluno aluno) {
        double media = mediaTotalNotas(aluno);
        return  (media >= 6) ? true : false;
    }//verifica se aprovado

    public static double idadeMedia() {
        List<Aluno> totalAlunos = new ArrayList<Aluno>();
        aprovados.stream().forEach(aluno -> totalAlunos.add(aluno));
        reprovados.stream().forEach(aluno -> totalAlunos.add(aluno));
        return totalAlunos.stream().mapToDouble(Aluno::getIdade).sum() / totalAlunos.size();
    }//media total das idades

    public static double mediaTotalNotas(Aluno aluno) {
        return aluno.getNotas().stream().mapToDouble(nota -> nota).sum() / aluno.getNotas().size();
    }//media total das notas

    public static String arredondar(double valor) {
        return new DecimalFormat("0.00").format(valor);
    }//arredonda o resultado

}//classe
