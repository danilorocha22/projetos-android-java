package com.danilo.signos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressWarnings("all")
public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //Declarar o Bundle para poder recuperar os dados enviados pela MainActivity
        Bundle args = getIntent().getBundleExtra("signo");

        //Verificar se o objeto signo realmente foi passado
        if(args != null) {
            Signo signoRecebido = (Signo) args.getSerializable("resultado");

            int imageResource = getResources().getIdentifier(signoRecebido.getImagem(), null,
                    getPackageName());

            Drawable res = ContextCompat.getDrawable(getApplicationContext(), imageResource);
            ImageView imagem_signo = (ImageView) findViewById(R.id.imgSigno);
            imagem_signo.setImageDrawable(res);

            TextView resultado = (TextView) findViewById(R.id.textSigno);
            TextView datas = (TextView) findViewById(R.id.textData);

            resultado.setText(signoRecebido.getNome());
            datas.setText("de "+ signoRecebido.getDiaInicio() +"/"+ signoRecebido.getMesInicio() +" "+
                    " até "+ signoRecebido.getDiaFim() +"/"+ signoRecebido.getMesFim());
        }

        //Referenciar o botão de voltar
        Button voltar = (Button) findViewById(R.id.buttonVoltar);

        //tratar o click no botão
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();//finalizar a ResultActivity e não a aplicação
            }
        });
    }


}