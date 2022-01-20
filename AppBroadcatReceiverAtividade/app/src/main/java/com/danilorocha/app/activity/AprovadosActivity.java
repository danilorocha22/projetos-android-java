package com.danilorocha.app.activity;

import static android.content.Intent.ACTION_BATTERY_CHANGED;
import static android.os.BatteryManager.ACTION_CHARGING;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.danilorocha.app.R;
import com.danilorocha.app.entity.Aluno;
import com.danilorocha.app.entity.Secretaria;

import java.util.ArrayList;
import java.util.List;

public class AprovadosActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView listViewAprovados;
    private ArrayAdapter adapter;
    private static List<Aluno> dados;
    private MyBroadcastReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aprovados);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listViewAprovados = findViewById(R.id.listViewAprovados);
        listViewAprovados.setOnItemClickListener(this);

        dados = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dados);
        listViewAprovados.setAdapter(adapter);

        receiver = new MyBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter(ACTION_CHARGING);
        registerReceiver(receiver, intentFilter);
    }//onCreate

    private class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(ACTION_CHARGING)) {
                toast("Ação: "+intent.getAction());
                adapter.clear();
                Secretaria.aprovados.stream().forEach(aluno -> dados.add(aluno));
                adapter.notifyDataSetChanged();
            } else
                Log.i("Log", "Ação: "+intent.getAction());
        }//onReceive
    }//inner classe

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //identifica a ação de voltar
            case android.R.id.home:
                //encerra a activity
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }//voltar

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Aluno aluno = (Aluno) adapterView.getAdapter().getItem(i);
        toast(aluno.getNome() +" está aprovado!");
    }//click no item da lista

    private void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }//toast

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }//onPause

}//classe