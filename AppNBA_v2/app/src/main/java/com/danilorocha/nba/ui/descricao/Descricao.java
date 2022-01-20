package com.danilorocha.nba.ui.descricao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Descricao {
    private int id;
    private String data;
    private String siglaTimeCasa;
    private String siglaTimeFora;

    public Descricao() {
    }

    public Descricao(int id, String data, String siglaTimeCasa, String siglaTimeFora) {
        this.id = id;
        this.data = data;
        this.siglaTimeCasa = siglaTimeCasa;
        this.siglaTimeFora = siglaTimeFora;
    }

    public String formatarData(String data) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(data);//Este método lança ParseException
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new SimpleDateFormat("dd/MM/yyyy").format(date);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getSiglaTimeCasa() {
        return siglaTimeCasa;
    }

    public void setSiglaTimeCasa(String siglaTimeCasa) {
        this.siglaTimeCasa = siglaTimeCasa;
    }

    public String getSiglaTimeFora() {
        return siglaTimeFora;
    }

    public void setSiglaTimeFora(String siglaTimeFora) {
        this.siglaTimeFora = siglaTimeFora;
    }

    @Override
    public String toString() {
        return "\nJogo Nº " + id + "\n" +
                "Data: " + data + "\n" +
                "Time Anfitrião: " + siglaTimeCasa + "\n" +
                "Time Visitante: " + siglaTimeFora +"\n";
    }

}
