package com.danilorocha.app.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.danilorocha.app.R;
import com.danilorocha.app.entity.Usuario;

import java.util.ArrayList;
import java.util.List;

public class ResultadoActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayAdapter<Usuario> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listView = findViewById(R.id.listResultado);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Usuario.listaUsuarios);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }//onCreate

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        //identificar a ação de voltar a tela
            case android.R.id.home:
                //encerra a activity
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }//onReturn

}//classe