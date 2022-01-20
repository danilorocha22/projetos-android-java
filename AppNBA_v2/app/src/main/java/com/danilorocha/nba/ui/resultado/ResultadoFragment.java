package com.danilorocha.nba.ui.resultado;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.danilorocha.nba.R;
import com.danilorocha.nba.dao.FabricaConexao;
import com.danilorocha.nba.dao.ResultadoDao;
import com.danilorocha.nba.dao.TimeCasaDao;
import com.danilorocha.nba.ui.timecasa.TimeCasa;
import com.danilorocha.nba.ui.timecasa.TimeCasaAdapter;

import java.util.List;

public class ResultadoFragment extends Fragment {
    private List<Resultado> lista;
    private RecyclerView recyclerView;
    private ResultadoAdapter adapter;
    private FabricaConexao conexao;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_resultado, container, false);

        conexao = new FabricaConexao(getContext());
        lista = new ResultadoDao(conexao).obterDados();

        recyclerView = root.findViewById(R.id.recyclerViewResultado);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new ResultadoAdapter(lista);
        recyclerView.setAdapter(adapter);
        adapter.setItemClick(getClick());

        return root;
    }//onCreateView

    private View.OnClickListener getClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
                int i = viewHolder.getAdapterPosition();
                Resultado r = lista.get(i);
                Toast.makeText(getContext(), r.toString(), Toast.LENGTH_SHORT).show();
            }
        };
    }//onClick

}//classe