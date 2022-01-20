package com.danilorocha.permissoes.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.airbnb.lottie.LottieAnimationView;
import com.danilorocha.permissoes.R;

public class ResultadoActivity extends AppCompatActivity {
    private LottieAnimationView lottiAnimacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        lottiAnimacao = findViewById(R.id.lottieAnimacao);
        Intent intent = getIntent();
        String permissao = intent.getStringExtra("permissao");
        setAnimacacao(permissao);
    }//onCreate

    private void setAnimacacao(String permissao) {
        if (permissao.equals(Manifest.permission.CAMERA))
            lottiAnimacao.setAnimation(R.raw.camera);
        if (permissao.equals(Manifest.permission.RECORD_AUDIO))
            lottiAnimacao.setAnimation(R.raw.microfone);
        if (permissao.equals(Manifest.permission.ACCESS_FINE_LOCATION))
            lottiAnimacao.setAnimation(R.raw.localizacao);
    }//metodo

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //identificar a ação de voltar a tela
            case android.R.id.home:
                //encerra a activity
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }//metodo

}//classe