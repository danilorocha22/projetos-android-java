package com.danilorocha.app.util;

import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.danilorocha.app.model.Contato;

import java.util.List;

public class AgendaUtil {
    private static String nome, fone, email;

    public static void mostrarContato(List<TextView> textViews, Contato contato) {
        textViews.get(0).setText(contato.getNome());
        textViews.get(1).setText(contato.getTelefone());
        textViews.get(2).setText(contato.getEmail());
    }//metodo

    public static boolean verificarInputs(List<EditText> inputs) {
        nome = inputs.get(0).getText().toString();
        fone = inputs.get(1).getText().toString();
        email = inputs.get(2).getText().toString();
        return (!nome.isEmpty() && !fone.isEmpty() && !email.isEmpty());
    }//metodo

    public static void carregarInputs(List<EditText> inputs, Contato contato) {
        inputs.get(0).setText(contato.getNome());
        inputs.get(1).setText(contato.getTelefone());
        inputs.get(2).setText(contato.getEmail());
    }//metodo

    public static void limparInputs(List<EditText> inputs) {
        inputs.get(0).setText("");
        inputs.get(1).setText("");
        inputs.get(2).setText("");
        inputs.get(0).requestFocus();
    }//metodo

    public static Contato instanciarContato() {
        return new Contato(nome, fone, email);
    }//metodo

    public static void toast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }//metodo
}//classe
