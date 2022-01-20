package com.danilo.pdm2atv1;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class SegundaActivity extends AppCompatActivity {
    private String dadosJSON;
    private ListView listView;
    private List<Estudante> lista;
    private ArrayAdapter<Estudante> arrayAdapter;
    private ExpandableListView expandableListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);
        
        dadosJSON = getIntent().getStringExtra("dados");
        listView = findViewById(R.id.listViewDados);
        lista = consumirJSON();
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lista);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Estudante estudante = lista.get(position);
                Toast.makeText(SegundaActivity.this, "Disciplina: "+estudante.getDisciplina() +
                        " - Nota: "+ estudante.getNota(), Toast.LENGTH_LONG).show();
            }
        });

    }

    private List<Estudante> consumirJSON() {
        List<Estudante> listaEstudantes = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(dadosJSON);
            JSONArray jsonArray = jsonObject.getJSONArray("estudantes");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                Estudante estudante = new Estudante();
                estudante.setNome(object.getString("nomeEstudante"));
                estudante.setDisciplina(object.getString("nomeDisciplina"));
                estudante.setNota(object.getInt("notaEstudante"));
                listaEstudantes.add(estudante);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listaEstudantes;
    }
}