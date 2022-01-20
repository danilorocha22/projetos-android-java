package com.danilorocha.app.entity;

import android.widget.ImageView;

import com.danilorocha.app.R;

public class Planeta {
    private String planeta;

    public Planeta(String nome) {
        this.planeta = nome;
    }

    public void setImagem(ImageView imagem) {
        switch (planeta) {
            case "Sol":
                imagem.setImageResource(R.drawable.sol);
                break;
            case "Mercúrio":
                imagem.setImageResource(R.drawable.mercurio);
                break;
            case "Vênus":
                imagem.setImageResource(R.drawable.venus);
                break;
            case "Terra":
                imagem.setImageResource(R.drawable.terra);
                break;
            case "Marte":
                imagem.setImageResource(R.drawable.marte);
                break;
            case "Saturno":
                imagem.setImageResource(R.drawable.saturno);
                break;
            case "Júpiter":
                imagem.setImageResource(R.drawable.jupiter);
                break;
            case "Urano":
                imagem.setImageResource(R.drawable.urano);
                break;
            case "Netuno":
                imagem.setImageResource(R.drawable.netuno);
                break;
        }//switch
    }//setar imagem do planeta

}//classe
