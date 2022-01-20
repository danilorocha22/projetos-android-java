package com.danilorocha.app.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.danilorocha.app.R;
import com.danilorocha.app.database.BDSQLite;
import com.danilorocha.app.model.Pessoa;
import com.danilorocha.app.model.PessoaAdapter;

import java.util.ArrayList;
import java.util.List;

public class ConsultaActivity extends AppCompatActivity {
    private BDSQLite bd;
    private RecyclerView recyclerView;
    private PessoaAdapter adapter;
    private List<Pessoa> pessoas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        bd = new BDSQLite(this);
        pessoas = bd.consultarPessoas();
        adapter = new PessoaAdapter(pessoas);
        recyclerView.setAdapter(adapter);
        adapter.setItemClick(getItemClick());
    }//onCreate

    private View.OnClickListener getItemClick() {
        return view -> {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int pos = viewHolder.getAdapterPosition();
            Toast.makeText(this, "ID: "+pessoas.get(pos).getId(), Toast.LENGTH_SHORT).show();
        };
    }//metodo

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }//metodo

}//classe