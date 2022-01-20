package com.danilorocha.imc.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.danilorocha.imc.R;
import com.danilorocha.imc.pessoa.Pessoa;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<TextInputEditText> listaEditText = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaEditText.add(findViewById(R.id.inputNome));
        listaEditText.add(findViewById(R.id.inputPeso));
        listaEditText.add(findViewById(R.id.inputAltura));
    }//metodo

    public void click(View v) {
        calcularIMC();
    }//click

    private void calcularIMC() {
        listaEditText.forEach(input -> input.getText().toString());
        String nome = listaEditText.get(0).getText().toString();
        String peso = listaEditText.get(1).getText().toString();
        String altura = listaEditText.get(2).getText().toString();

        if (!nome.isEmpty() && !peso.isEmpty() && !altura.isEmpty()) {
            Pessoa pessoa = new Pessoa(nome, Float.parseFloat(peso), Float.parseFloat(altura));
            pessoa.setImc();
            resultado(pessoa);
        } else
            Toast.makeText(this,"Informe todos os campos", Toast.LENGTH_SHORT).show();
    }//calcular IMC

    private void resultado(Pessoa pessoa) {
        Intent intent = new Intent(this, ResultadoActivity.class);
        intent.putExtra("pessoa", pessoa);
        startActivity(intent);
        finish();
    }//mostrar resultado

}//classe