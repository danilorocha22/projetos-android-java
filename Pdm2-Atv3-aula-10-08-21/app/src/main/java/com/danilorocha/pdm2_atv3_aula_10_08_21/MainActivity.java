package com.danilorocha.pdm2_atv3_aula_10_08_21;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("all")
public class MainActivity extends AppCompatActivity {
    private ListView listViewDadosId;
    private StringBuilder builder;
    private ArrayAdapter adapter;
    private List<Item> dadosBaixados;
    private static final String URL = "https://jsonplaceholder.typicode.com/posts";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listViewDadosId = (ListView) findViewById(R.id.dadosId);
        new obterDados().execute();
    }//onCreate

    private class obterDados extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            toast("Downloading: arquivo JSON");
        }//onPreExecute

        @Override
        protected Void doInBackground(Void... voids) {
            InputStream inputStream = Conexao.obterRespostaHTTP(URL);
            String textoJSON = Auxilia.converter(inputStream);
            Gson gson = new Gson();
            builder = new StringBuilder();

            if (textoJSON != null) {
                Type type = new TypeToken<List<Item>>(){}.getType();
                dadosBaixados = gson.fromJson(textoJSON, type);
                dadosBaixados.stream().forEach(item -> builder.append(item.toString()+"\n"));
            } else {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        toast("não foi possível obter JSON pelo servidor");
                    }
                });
            }
            return null;
        }//doInBackground

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1,
                    Collections.singletonList(builder));
            listViewDadosId.setAdapter(adapter);
        }//onPostExecute

    }//classe interna

    private void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }//toast

}//classe