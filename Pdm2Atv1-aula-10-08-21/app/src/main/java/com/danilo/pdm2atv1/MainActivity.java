package com.danilo.pdm2atv1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class MainActivity extends AppCompatActivity {
    private EditText editTextNome, editTextDisciplina, editTextNota;
    private List<Estudante> estudantes;
    private TextView textViewResultado;
    private String retorno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextNome = findViewById(R.id.editTextNome);
        editTextDisciplina = findViewById(R.id.editTextDisciplina);
        editTextNota = findViewById(R.id.editTextNota);
        textViewResultado = findViewById(R.id.textViewResultado);
        estudantes = new ArrayList<>();
    }

    public void criarListaDeEstudantes(View view) {
        estudantes.add(new Estudante(
                editTextNome.getText().toString(),
                editTextDisciplina.getText().toString(),
                Integer.parseInt(editTextNota.getText().toString())));
        Toast.makeText(getApplicationContext(), "Item inserido com sucesso!", Toast.LENGTH_LONG).show();
    }

    public String criarJSON(List<Estudante> estudantes) {
        Gson gson = new Gson();
        String stringJson = gson.toJson(estudantes);
        return stringJson;
    }

    public void gerarJSON(View view) {
        retorno = criarJSON(estudantes);
        textViewResultado.setText(retorno);
    }

    public void abrirTela(View view) {
        Intent intent = new Intent(getApplicationContext(), SegundaActivity.class);
        intent.putExtra("dados", retorno);
        startActivity(intent);
    }
}