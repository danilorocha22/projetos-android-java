package com.danilorocha.pdm2.ui.filme;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.danilorocha.pdm2.MainActivity;
import com.danilorocha.pdm2.Placar;
import com.danilorocha.pdm2.R;

import kotlin.reflect.KFunction;

public class FilmesFragment extends Fragment {
    private Context context;
    private ImageView imgViewTituloDica, imgViewDica;
    private EditText editTextResposta;
    private Button btnResposta, btnVerDica;
    private static final String RESPOSTA_CERTA = "Game of Thrones",
            CERTO = "Resposta certa", ERRADO = "Resposta errada",
            AVISO = "Insira a resposta";
    private int ct=0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                            ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_filmes, container, false);
        context = container.getContext();
        imgViewTituloDica = root.findViewById(R.id.imgViewTituloDica);
        imgViewDica = root.findViewById(R.id.imgViewDica);
        editTextResposta = root.findViewById(R.id.editTextResposta);
        btnResposta = root.findViewById(R.id.btnResposta);
        btnVerDica = root.findViewById(R.id.btnVerDica);
        btnVerDica.setOnClickListener(start(imgViewTituloDica, imgViewDica));

        return root;
    }//metodo

    public View.OnClickListener start(ImageView imgViewTituloDica, ImageView imgViewDica) {
        return new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (ct) {
                    case 0:
                        imgViewTituloDica.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.primeira_dica));
                        imgViewDica.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.sete_reinos));
                        editTextResposta.setVisibility(View.VISIBLE);
                        btnResposta.setVisibility(View.VISIBLE);
                        btnVerDica.setVisibility(View.INVISIBLE);
                        btnResposta.setOnClickListener(avaliarResposta(editTextResposta, ct));
                        break;

                    case 1:
                        editTextResposta.setText("");
                        imgViewTituloDica.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.segunda_dica));
                        imgViewDica.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.casa_targaryen));
                        btnResposta.setOnClickListener(avaliarResposta(editTextResposta, ct));
                        break;

                    case 2:
                        imgViewTituloDica.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.terceira_dica));
                        imgViewDica.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.trono_de_ferro));
                        btnResposta.setOnClickListener(avaliarResposta(editTextResposta, ct));
                        btnVerDica.setVisibility(View.INVISIBLE);
                        break;
                }
                ct++;
            }
        };
    }//método

    public View.OnClickListener avaliarResposta(EditText editTextResposta, int ct) {
        return new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                String resposta = editTextResposta.getText().toString();
                if (resposta.equalsIgnoreCase(RESPOSTA_CERTA) && ct == 0) {
                    pontuar(3);
                } else if (resposta.equalsIgnoreCase(RESPOSTA_CERTA) && ct == 1) {
                    pontuar(2);
                } else if (resposta.equalsIgnoreCase(RESPOSTA_CERTA) && ct == 2) {
                    pontuar(1);
                } else if (!resposta.isEmpty()){
                    Toast.makeText(context, ERRADO, Toast.LENGTH_SHORT).show();
                    editTextResposta.setText("");
                    if (ct == 2) trocarTela(); else trocarDica();
                } else {
                    Toast.makeText(context, AVISO, Toast.LENGTH_SHORT).show();
                };
            }
        };
    }//metodo

    private void pontuar(int ponto) {
        Placar.setPonto(ponto);
        editTextResposta.setText("");
        Toast.makeText(context, CERTO, Toast.LENGTH_SHORT).show();
        trocarTela();
    }//metodo

    private void trocarDica() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                btnVerDica.performClick();
            }
        },800);
    }//metodo

    private void trocarTela() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
            }
        },800);
    }//metodo

}//classe