package com.danilorocha.app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.danilorocha.app.R;
import com.danilorocha.app.entity.Pesquisa;

public class ResultadoActivity extends AppCompatActivity {
    private TextView textView;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_resultado);
        textView = findViewById(R.id.txtViewResultado);
        imageView = findViewById(R.id.imageViewResultado);

        Intent intent = getIntent();
        Pesquisa pesquisa = (Pesquisa) intent.getSerializableExtra("pesquisa");
        mostrarResultado(pesquisa);
    }//onCreate

    private void mostrarResultado(Pesquisa pesquisa) {
        textView.setText(pesquisa.toString());
        setImagem(pesquisa.getResposta());
    }//mostrar resultado

    private void setImagem(String resposta) {
        switch (resposta) {
            case "Ruim":
                imageView.setImageResource(R.drawable.insatisfeito);
                break;
            case "Bom":
                imageView.setImageResource(R.drawable.indiferente);
                break;
            case "Ótimo":
                imageView.setImageResource(R.drawable.satisfeito);
                break;
        }
    }//setar imagem

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }//voltar

}//classe