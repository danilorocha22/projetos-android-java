package com.danilorocha.nba.ui.timecasa;

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
import com.danilorocha.nba.dao.FabricaConexao;
import com.danilorocha.nba.dao.TimeCasaDao;

import java.util.List;

public class TimeCasaFragment extends Fragment {
    private List<TimeCasa> lista;
    private RecyclerView recyclerView;
    private TimeCasaAdapter adapter;
    private FabricaConexao conexao;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_time_casa, container, false);

        conexao = new FabricaConexao(getContext());
        lista = new TimeCasaDao(conexao).obterDados();

        recyclerView = root.findViewById(R.id.recyclerViewTimeCasa);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new TimeCasaAdapter(lista);
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
                TimeCasa tc = lista.get(i);
                Toast.makeText(getContext(), tc.toString(), Toast.LENGTH_SHORT).show();
            }
        };
    }//onClick

}//classe