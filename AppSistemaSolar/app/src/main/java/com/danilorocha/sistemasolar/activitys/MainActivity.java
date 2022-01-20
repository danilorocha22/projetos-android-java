package com.danilorocha.sistemasolar.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.danilorocha.sistemasolar.R;
import com.danilorocha.sistemasolar.entitys.SistemaSolar;
import com.danilorocha.sistemasolar.entitys.Planeta;
import com.danilorocha.sistemasolar.entitys.PlanetaAdapter;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = findViewById(R.id.lvPlanetas);
        PlanetaAdapter adapter = new PlanetaAdapter(this, SistemaSolar.planetas());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }//onCreate

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Planeta planeta = SistemaSolar.planetas().get(i);
        Toast.makeText(getApplicationContext(), planeta.getNome(), Toast.LENGTH_SHORT).show();
    }//onItemClick

}//classe