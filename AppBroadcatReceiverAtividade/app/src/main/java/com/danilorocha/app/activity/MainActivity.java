package com.danilorocha.app.activity;

import static android.net.wifi.aware.WifiAwareManager.ACTION_WIFI_AWARE_STATE_CHANGED;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.danilorocha.app.R;
import com.danilorocha.app.entity.Aluno;
import com.danilorocha.app.entity.Secretaria;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Aluno aluno;
    private MyBroadcastReceiver receiver;
    private EditText nomeAluno, idadeAluno;
    private List<EditText> listaNotas = new ArrayList<EditText>();
    private final String SUCESSO = "sucesso", AVISO = "aviso";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nomeAluno = findViewById(R.id.inputNome);
        idadeAluno = findViewById(R.id.inputIdade);
        listaNotas.add(findViewById(R.id.inputNota1));
        listaNotas.add(findViewById(R.id.inputNota2));
        listaNotas.add(findViewById(R.id.inputNota3));
        listaNotas.add(findViewById(R.id.inputNota4));

        receiver = new MyBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter(ACTION_WIFI_AWARE_STATE_CHANGED);
        registerReceiver(receiver, intentFilter);
    }//onCreate

    public void click(View v) {
        switch (v.getId()) {
            case R.id.btnCadastrar:
                cadastrarAluno();
                break;

            case R.id.btnAprovados:
                verAprovados();
                break;

            case R.id.btnReprovados:
                verReprovados();
                break;

            case R.id.btnMediaIdade:
                Intent intent = new Intent();
                intent.putExtra("idadeMedia", String.valueOf(Secretaria.idadeMedia()));
                intent.setAction(ACTION_WIFI_AWARE_STATE_CHANGED);
                sendBroadcast(intent);
                break;
        }//swich
    }//click

    private void cadastrarAluno() {
        String nome = nomeAluno.getText().toString();
        String idade = idadeAluno.getText().toString();
        listaNotas.stream().forEach(nota -> nota.getText());
        int ct = 0;
        try {
            if (!nome.isEmpty() && !idade.isEmpty()) {
                aluno = criarAluno(nome, Integer.valueOf(idade));
                for (EditText nota : listaNotas) {
                    if (!nota.getText().toString().isEmpty()) {
                        aluno.setNotas(Double.valueOf(nota.getText().toString()));
                        ct++;
                        if (ct == 4) {
                            double media = Secretaria.mediaTotalNotas(aluno);
                            aluno.setMediaNotas(media);
                            Secretaria.cadastrar(aluno);
                            limparInputs();
                            toast(SUCESSO);
                        }
                    } else {
                        toast(AVISO);
                        break;
                    }
                }
            } else
                toast(AVISO);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }//cadastra aluno

    private Aluno criarAluno(String nome, int idade) {
        return new Aluno(nome, idade);
    }//cria aluno

    private void verAprovados() {
        Intent intent = new Intent(this, AprovadosActivity.class);
        startActivity(intent);
    }//aprovados

    private void verReprovados() {
        Intent intent = new Intent(this, ReprovadosActivity.class);
        startActivity(intent);
    }//reprovados

    private class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String idadeMedia = intent.getStringExtra("idadeMedia");

            if (intent.getAction().equals(ACTION_WIFI_AWARE_STATE_CHANGED)) {
                Toast.makeText(context, "Ação: " + intent.getAction(), Toast.LENGTH_LONG).show();
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Idade Média dos Alunos")
                        .setMessage("Resultado: " + idadeMedia + " anos")
                        .setCancelable(false)
                        .setNeutralButton("Fechar", null)
                        .show();
            } else
                Log.i("Log", "Ação: " + intent.getAction());
        }//onReceive
    }//classe interna

    private void toast(String tipo) {
        ViewGroup vg = findViewById(R.id.container_toast);
        Toast toast = new Toast(this);
        View v;
        switch (tipo) {
            case SUCESSO:
                v = getLayoutInflater().inflate(R.layout.toast_sucesso, vg);
                toast.setView(v);
                toast.setDuration(0);
                toast.show();
                break;

            case AVISO:
                v = getLayoutInflater().inflate(R.layout.toast_aviso, vg);
                toast.setView(v);
                toast.setDuration(0);
                toast.show();
                break;
        }//switch
    }//toast customizado

    private void limparInputs() {
        nomeAluno.getText().clear();
        idadeAluno.getText().clear();
        listaNotas.stream().forEach(nota -> nota.getText().clear());
        nomeAluno.requestFocus();
    }//limpar inputs

}//classe