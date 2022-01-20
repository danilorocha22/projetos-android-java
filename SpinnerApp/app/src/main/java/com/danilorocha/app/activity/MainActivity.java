package com.danilorocha.app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.danilorocha.app.R;
import com.danilorocha.app.entity.Planeta;

public class MainActivity extends AppCompatActivity {
    private ArrayAdapter<CharSequence> adapter;
    private Spinner spinner;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        spinner = (Spinner) findViewById(R.id.spinner);
        imageView = (ImageView) findViewById(R.id.imageView);

        adapter = ArrayAdapter.createFromResource(this, R.array.meu_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(mostrarPlaneta());
    }//onCreate

    private AdapterView.OnItemSelectedListener mostrarPlaneta() {
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String nome = spinner.getSelectedItem().toString();
                if (!nome.equals("Selecione:")) {
                    Planeta planeta = new Planeta(nome);
                    planeta.setImagem(imageView);
                } else
                    imageView.setImageDrawable(null);
            }//onItemSelected

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
    }//metodo que mostra o planeta

}//classe