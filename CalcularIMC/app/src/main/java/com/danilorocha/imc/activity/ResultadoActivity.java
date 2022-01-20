package com.danilorocha.imc.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.danilorocha.imc.R;
import com.danilorocha.imc.activity.MainActivity;
import com.danilorocha.imc.pessoa.Pessoa;

public class ResultadoActivity extends AppCompatActivity {
    private final float IMC_BAIXO = 18.5F, IMC_NORMAL = 26F;
    private TextView txtViewNome, txtViewIMC, txtResult;
    private Pessoa pessoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        txtViewNome = findViewById(R.id.txtNome);
        txtViewIMC = findViewById(R.id.txtIMC);
        txtResult = findViewById(R.id.txtResultado);
        pessoa = (Pessoa) getIntent().getSerializableExtra("pessoa");
        resultado();
    }//metodo

    public void resultado() {
        txtViewNome.setText("Olá " + pessoa.getNome());
        txtViewIMC.setText("Seu IMC é " + pessoa.formatarValor(pessoa.getImc()));

        if (pessoa.getImc() < IMC_BAIXO)
            txtResult.setText("Você está abaixo do peso ideal.");
        else if (pessoa.getImc() < IMC_NORMAL)
            txtResult.setText("Parabéns, você está no seu peso ideal.");
        else
            txtResult.setText("Você está acima do peso ideal (sobrepeso).");
    }//metodo

    public void voltar(View v) {
        Intent intent = new Intent(ResultadoActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }//voltar

}//classe