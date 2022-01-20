package com.danilorocha.cardviewapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.danilorocha.cardviewapp.R;
import com.danilorocha.cardviewapp.entity.Pessoa;
import com.danilorocha.cardviewapp.entity.PessoaAdapter;
import com.danilorocha.cardviewapp.entity.Pessoas;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PessoaAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Pessoa> lista = new ArrayList<Pessoa>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        lista = Pessoas.lista;

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new PessoaAdapter(lista);
        recyclerView.setAdapter(adapter);
        adapter.setItemClick(getItemClick());
    }//onCreate

    private View.OnClickListener getItemClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
                int pos = viewHolder.getAdapterPosition();
                dialog(lista.get(pos));
            }
        };
    }//metodo

    private void dialog(Pessoa p) {
        AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.setTitle("Qualificações de " + p.getNome());
        dialog.setMessage(p.getQualificacao());
        dialog.setButton(DialogInterface.BUTTON_NEUTRAL, "Fechar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        dialog.show();
    }//metodo

}//classe