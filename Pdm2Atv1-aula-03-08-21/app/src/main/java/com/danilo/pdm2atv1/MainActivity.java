package com.danilo.pdm2atv1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class MainActivity extends AppCompatActivity {
    private EditText editTextNome, editTextDisciplina, editTextNota;
    private Button btnAdicionar, btnGerar, btnConsumir;
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
        btnAdicionar = findViewById(R.id.btnAdicionar);
        btnGerar = findViewById(R.id.btnGerar);
        btnConsumir = findViewById(R.id.btnConsumir);
        estudantes = new ArrayList<>();
    }

    public void criarListaDeEstudantes(View view) {
        estudantes.add(new Estudante(
            editTextNome.getText().toString(),
            editTextDisciplina.getText().toString(),
            Integer.parseInt(editTextNota.getText().toString())));
        Toast.makeText(getApplicationContext(), "Item inserido com sucesso!", Toast.LENGTH_LONG).show();
    }

    public String criarJSON() {
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < estudantes.size(); i++) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("nomeEstudante", estudantes.get(i).getNome());
                jsonObject.put("nomeDisciplina", estudantes.get(i).getDisciplina());
                jsonObject.put("notaEstudante", estudantes.get(i).getNota());
                jsonArray.put(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return "{estudantes: "+ jsonArray.toString() +"}";
    }

    public void gerarJSON(View view) {
        retorno = criarJSON();
        textViewResultado.setText(retorno);
    }

    public void abrirTela(View view) {
        Intent intent = new Intent(getApplicationContext(), SegundaActivity.class);
        intent.putExtra("dados", retorno);
        startActivity(intent);
    }
}