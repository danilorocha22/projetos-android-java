package com.danilorocha.pdm2.ui.maca;

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
public class MacaFragment extends Fragment {

    public  View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_maca, container, false);
        TextView textView = root.findViewById(R.id.textViewMaca);
        ImageView imageView = root.findViewById(R.id.imageViewMaca);
        textView.setText("Maçã");
        imageView.setImageResource(R.drawable.apple);

        return root;
    }//metodo
}