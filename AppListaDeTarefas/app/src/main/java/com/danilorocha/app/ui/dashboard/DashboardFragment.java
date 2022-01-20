package com.danilorocha.app.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.danilorocha.app.MainActivity;
import com.danilorocha.app.R;
import com.danilorocha.app.databinding.FragmentDashboardBinding;
import com.danilorocha.app.ui.novaTarefa.NovaTarefaFragment;

public class DashboardFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);


        return root;
    }//onCreateView

}//classe