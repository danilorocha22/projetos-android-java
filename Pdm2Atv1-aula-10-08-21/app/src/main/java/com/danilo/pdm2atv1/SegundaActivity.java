package com.danilo.pdm2atv1;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

@SuppressWarnings("all")
public class SegundaActivity extends AppCompatActivity {
    private String dadosJSON;
    private ListView listView;
    private List<Estudante> lista;
    private ArrayAdapter<Estudante> adapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);
        listView = findViewById(R.id.listViewDados);

        dadosJSON = getIntent().getStringExtra("dados");
        lista = consumirJSON();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lista);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(clickItemLista());

    }//onCreate

    private AdapterView.OnItemClickListener clickItemLista() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Estudante estudante = lista.get(position);
                toast("Nota: "+ estudante.getNota());
            }
        };
    }//mostrar o click na lista

    private List<Estudante> consumirJSON() {
        String resultado = "";
        List<Estudante> listaEstudantes = null;

        if (dadosJSON!=null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Estudante>>(){}.getType();
            listaEstudantes = gson.fromJson(dadosJSON, type);
            toast(listaEstudantes.toString());
        }
        return listaEstudantes;
    }//fazer o parse de json para lista de estudantes

    private void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }//toast

}//classe