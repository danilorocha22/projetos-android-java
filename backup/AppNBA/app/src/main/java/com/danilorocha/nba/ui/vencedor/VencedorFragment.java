package com.danilorocha.nba.ui.vencedor;

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
import com.danilorocha.nba.dao.TimeCasaDao;
import com.danilorocha.nba.dao.VencedorDao;
import com.danilorocha.nba.ui.timecasa.TimeCasa;
import com.danilorocha.nba.ui.timecasa.TimeCasaAdapter;

import java.util.List;

public class VencedorFragment extends Fragment {
    private List<Vencedor> lista;
    private RecyclerView recyclerView;
    private VencedorAdapter adapter;
    private FabricaConexao conexao;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_vencedor, container, false);

        conexao = new FabricaConexao(getContext());
        lista = new VencedorDao(conexao).obterDados();

        recyclerView = root.findViewById(R.id.recyclerViewVencedor);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new VencedorAdapter(lista);
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
                Vencedor v = lista.get(i);
                Toast.makeText(getContext(), v.toString(), Toast.LENGTH_SHORT).show();
            }
        };
    }//onClick

}//classe