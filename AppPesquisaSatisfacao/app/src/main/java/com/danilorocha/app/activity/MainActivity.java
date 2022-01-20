package com.danilorocha.app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import com.danilorocha.app.R;
import com.danilorocha.app.entity.CidadeUtil;
import com.danilorocha.app.entity.Pesquisa;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    private TextInputEditText inputNome;
    private SeekBar seekBar;
    private ImageView imageView;
    private ArrayAdapter<CharSequence> adapter;
    private AutoCompleteTextView inputAutoCompleteCidade;
    private int progresso;
    private static final int RUIM = 0, BOM = 1, OTIMO = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, CidadeUtil.cidades);
        inputNome = findViewById(R.id.inputNome);
        seekBar = findViewById(R.id.seekBar);
        imageView = findViewById(R.id.imageView);
        inputAutoCompleteCidade = findViewById(R.id.inputAutoCompleteCidade);
        inputAutoCompleteCidade.setAdapter(adapter);
        seekBar.setOnSeekBarChangeListener(setImagem());
    }//onCreate

    private void criarPesquisa() {
        String usuario = inputNome.getText().toString();
        String cidade = inputAutoCompleteCidade.getText().toString();
        String resposta = getResposta();

        try {
            if (!usuario.isEmpty() && !cidade.isEmpty() && !resposta.isEmpty()) {
                Pesquisa pesquisa = new Pesquisa(usuario, cidade, resposta);
                mostrarResultado(pesquisa);
            } else {
                Toast.makeText(this, "Informe todos os campos", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//criar pesquisa

    private void mostrarResultado(Pesquisa pesquisa) {
        Intent intent = new Intent(this, ResultadoActivity.class);
        intent.putExtra("pesquisa", pesquisa);
        startActivity(intent);
        limparInputs();
    }//mostrar resultado

    private String getResposta() {
        switch (progresso) {
            case RUIM: return "Ruim";
            case BOM: return "Bom";
            case OTIMO: return "Ótimo";
            default: return null;
        }
    }//pegar resposta

    private SeekBar.OnSeekBarChangeListener setImagem() {
        return new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progresso = progress;
                switch (progresso) {
                    case RUIM:
                        imageView.setImageResource(R.drawable.ruim);
                        break;
                    case BOM:
                        imageView.setImageResource(R.drawable.bom);
                        break;
                    case OTIMO:
                        imageView.setImageResource(R.drawable.otimo);
                        break;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        };
    }//trocar imagem conforme progresso

    private void limparInputs() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                inputNome.setText("");
                inputAutoCompleteCidade.setText("");
                seekBar.setProgress(0);
                inputNome.requestFocus();
            }
        }, 1200);
    }//limpar inputs

    public void click(View v) {
        criarPesquisa();
    }//click

}//classe