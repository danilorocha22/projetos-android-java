package com.danilorocha.pdm2.ui.biscoito;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.danilorocha.pdm2.R;

@SuppressWarnings("all")
public class BiscoitoFragment extends Fragment {

    public  View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_biscoito, container, false);
        TextView textView = root.findViewById(R.id.textViewBiscoito);
        ImageView imageView = root.findViewById(R.id.imageViewBiscoito);
        textView.setText("Biscoito");
        imageView.setImageResource(R.drawable.biscuit);

        return root;
    }//metodo

}//classe