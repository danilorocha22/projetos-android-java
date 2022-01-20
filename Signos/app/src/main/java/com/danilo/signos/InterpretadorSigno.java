package com.danilo.signos;

import java.util.ArrayList;

@SuppressWarnings("all")
public class InterpretadorSigno {

    private ArrayList<Signo> signos = new ArrayList<Signo>() {{//Double Bracket Initialization
       add(new Signo(20, 18, 1, 2, "Aquário", "@drawable/aquario"));
       add(new Signo(19, 20, 2, 3, "Peixes", "@drawable/peixes"));
       add(new Signo(21, 19, 3, 4, "Aries", "@drawable/aries"));
       add(new Signo(20, 20, 4, 5, "Touro", "@drawable/touro"));
       add(new Signo(21, 20, 5, 6, "Gêmeos", "@drawable/gemeos"));
       add(new Signo(21, 22, 6, 7, "Câncer", "@drawable/cancer"));
       add(new Signo(23, 22, 7, 8, "Leão", "@drawable/leao"));
       add(new Signo(23, 22, 8, 9, "Virgme", "@drawable/virgem"));
       add(new Signo(23, 22, 9, 10, "Libra", "@drawable/libra"));
       add(new Signo(23, 21, 10, 11, "Escorpião", "@drawable/escorpiao"));
       add(new Signo(22, 21, 11, 12, "Sagitário", "@drawable/sagitario"));
       add(new Signo(22, 19, 12, 1, "Capricórnio", "@drawable/capricornio"));
    }};

    public Signo interpretar(int dia, int mes) {
        Signo signo = null;

        for (Signo s : signos) {
            if (s.getMesInicio() == mes && dia >= s.getDiaInicio()) {
                signo = s;
                break;
            } else if (s.getMesFim() == mes && dia <= s.getDiaFim()) {
                signo = s;
                break;
            }
        }
        return signo;
    }


}
