package com.danilorocha.app.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.danilorocha.app.R;
import com.danilorocha.app.model.Agenda;
import com.danilorocha.app.model.Disciplina;
import com.danilorocha.app.factory.FactoryDisciplina;

import java.util.ArrayList;
import java.util.List;

public class QuestionarioActivity extends AppCompatActivity {
    private TextView txtTitulo, txtPergunta;
    private RadioGroup radioGroup;
    private Disciplina disciplina;
    private List<RadioButton> alternativas = new ArrayList<>();
    private LottieAnimationView lottie;
    int questao = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionario);
        txtTitulo = findViewById(R.id.tituloQuestionario);
        txtPergunta = findViewById(R.id.pergunta);
        radioGroup = findViewById(R.id.radioGroup);
        lottie = findViewById(R.id.lottieAnimacao);
        alternativas.add(findViewById(R.id.alternativaA));
        alternativas.add(findViewById(R.id.alternativaB));
        alternativas.add(findViewById(R.id.alternativaC));
        alternativas.add(findViewById(R.id.alternativaD));
        disciplina = FactoryDisciplina.newInstance(Agenda.getDisciplina());
        montarQuestao();
    }//onCreate

    public void click(View view) {
        int id = radioGroup.getCheckedRadioButtonId();
        if(id == -1) {
            toast("Marque uma das alternativas");
        } else  {
            toast(disciplina.pontuar(questao, pegarResposta(id)));
            proximaQuestao();
        }//else
    }//click

    private String pegarResposta(int id) {
        View radioView = radioGroup.findViewById(id);
        int idx = radioGroup.indexOfChild(radioView);
        RadioButton radioButton = (RadioButton) radioGroup.getChildAt(idx);
        return radioButton.getText().toString();
    }//metodo

    private void proximaQuestao() {
        questao++;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                montarQuestao();
            }
        }, 2500);
        radioGroup.clearCheck();
    }//metodo

    private void montarQuestao() {
        switch (questao) {
            case 1: questao1(); break;
            case 2: questao2(); break;
            case 3: questao3(); break;
            case 4: mostrarPontuação();break;
        }//switch
    }//metodo

    private void questao1() {
        txtTitulo.setText(Agenda.getDisciplina());
        txtPergunta.setText(disciplina.getQUESTAO_1());
        for (int i = 0; i < alternativas.size(); i++)
            alternativas.get(i).setText(disciplina.getALTERNATIVAS_QUESTAO_1().get(i));
    }//metodo

    private void questao2() {
        txtTitulo.setText(Agenda.getDisciplina());
        txtPergunta.setText(disciplina.getQUESTAO_2());
        for (int i = 0; i < alternativas.size(); i++)
            alternativas.get(i).setText(disciplina.getALTERNATIVAS_QUESTAO_2().get(i));
    }//metodo

    private void questao3() {
        txtTitulo.setText(Agenda.getDisciplina());
        txtPergunta.setText(disciplina.getQUESTAO_3());
        for (int i = 0; i < alternativas.size(); i++)
            alternativas.get(i).setText(disciplina.getALTERNATIVAS_QUESTAO_3().get(i));
    }//metodo

    private void mostrarPontuação() {
        if (lottie.getParent() != null) {
            ((ViewGroup) lottie.getParent()).removeView(lottie);
            lottie.setVisibility(View.VISIBLE);
            new AlertDialog.Builder(this)
                    .setTitle("Tarefa do dia cumprida!")
                    .setMessage("Acertos: "+ Disciplina.getPontos())
                    .setCancelable(false)
                    .setView((View) lottie)
                    .setPositiveButton("Fechar", (dialog, which) -> finalizar())
                    .show();
        }//if
    }//metodo

    private void finalizar() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        Disciplina.setPontos(0);
        finish();
    }//metodo

    private void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }//metodo

}//classe