package com.danilorocha.appjsonserver.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.danilorocha.appjsonserver.R;
import com.danilorocha.appjsonserver.model.Contato;
import com.danilorocha.appjsonserver.service.ContatoService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private List<Contato> contatos;
    private ListView listViewContatos;
    private ArrayAdapter<Contato> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listViewContatos = findViewById(R.id.listViewContatos);
        contatos = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ContatoService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ContatoService service = retrofit.create(ContatoService.class);
        Call<List<Contato>> call = service.getContatos();

        call.enqueue(new Callback<List<Contato>>() {
            @Override
            public void onResponse(Call<List<Contato>> call, Response<List<Contato>> response) {
                try {
                    if (response.isSuccessful()) {
                        contatos.addAll(response.body());
                        adapter = new ArrayAdapter<>(MainActivity.this,
                                android.R.layout.simple_list_item_1, contatos);
                        listViewContatos.setAdapter(adapter);
                        Log.i("Sucess onResponse", call.request().toString());
                    } else
                        Log.i("Erro onResponse", call.request().toString());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<Contato>> call, Throwable t) {
                Toast.makeText(MainActivity.this,
                        "Não foi possível obter os dados de https://localhost:3000/contato",
                        Toast.LENGTH_SHORT).show();
                Log.i("Log onFailure", t.getMessage());
            }
        });
    }

}//classe