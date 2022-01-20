package com.danilorocha.pdm2.ui.bolo;

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
public class BoloFragment extends Fragment {

    public  View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_bolo, container, false);
        TextView textView = root.findViewById(R.id.textViewBolo);
        ImageView imageView = root.findViewById(R.id.imageViewBolo);
        textView.setText("Bolo");
        imageView.setImageResource(R.drawable.birthday);

        return root;
    }//metodo
}//classe