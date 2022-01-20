package com.danilorocha.nba.ui.timefora;

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
import com.danilorocha.nba.dao.TimeForaDao;
import com.danilorocha.nba.ui.timecasa.TimeCasa;
import com.danilorocha.nba.ui.timecasa.TimeCasaAdapter;

import java.util.List;

public class TimeForaFragment extends Fragment {
    private List<TimeFora> lista;
    private RecyclerView recyclerView;
    private TimeForaAdapter adapter;
    private FabricaConexao conexao;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_time_visitante, container, false);

        conexao = new FabricaConexao(getContext());
        lista = new TimeForaDao(conexao).obterDados();

        recyclerView = root.findViewById(R.id.recyclerViewTimeVisitante);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new TimeForaAdapter(lista);
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
                TimeFora tf = lista.get(i);
                Toast.makeText(getContext(), tf.toString(), Toast.LENGTH_SHORT).show();
            }
        };
    }//onClick

}//classe