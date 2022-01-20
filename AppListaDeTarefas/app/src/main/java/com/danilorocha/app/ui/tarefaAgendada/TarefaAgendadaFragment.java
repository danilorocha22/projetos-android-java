package com.danilorocha.app.ui.tarefaAgendada;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.danilorocha.app.R;

public class TarefaAgendadaFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_tarefa_agendada, container, false);

        return root;
    }//onCreateView

}//classe