package com.danilorocha.nba.ui.descricao;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.danilorocha.nba.R;
import com.danilorocha.nba.dao.DescricaoDao;
import com.danilorocha.nba.dao.FabricaConexao;

import java.util.ArrayList;
import java.util.List;

public class DescricaoFragment extends Fragment {
    private List<Descricao> lista = new ArrayList<>();
    private RecyclerView recyclerView;
    private DescricaoAdapter adapter;
    private FabricaConexao conexao;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_descricao, container, false);

        conexao = new FabricaConexao(getContext());
        lista = new DescricaoDao(conexao).obterDados();

        recyclerView = root.findViewById(R.id.recyclerViewDescricao);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new DescricaoAdapter(lista);
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
                Descricao d = lista.get(i);
                Toast.makeText(getContext(), d.toString(), Toast.LENGTH_SHORT).show();
            }
        };
    }//onClick

}//classe