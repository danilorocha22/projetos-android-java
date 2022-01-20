package com.danilorocha.app.activity;

import static android.content.Intent.ACTION_BOOT_COMPLETED;
import static android.net.wifi.WifiManager.ACTION_WIFI_NETWORK_SUGGESTION_POST_CONNECTION;
import static android.net.wifi.aware.WifiAwareManager.ACTION_WIFI_AWARE_STATE_CHANGED;
import static android.net.wifi.rtt.WifiRttManager.ACTION_WIFI_RTT_STATE_CHANGED;
import static android.os.BatteryManager.ACTION_DISCHARGING;
import static android.telephony.SubscriptionManager.ACTION_DEFAULT_SMS_SUBSCRIPTION_CHANGED;

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

import androidx.appcompat.app.AppCompatActivity;

import com.danilorocha.app.R;
import com.danilorocha.app.entity.Aluno;
import com.danilorocha.app.entity.Secretaria;

import java.util.ArrayList;
import java.util.List;

public class ReprovadosActivity extends AppCompatActivity implements AdapterView.OnItemClickListener  {
    private ListView listViewReprovados;
    private ArrayAdapter<Aluno> adapter;
    private List<Aluno> dados;
    private MyBroadcastReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reprovados);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listViewReprovados = findViewById(R.id.listViewReprovados);
        listViewReprovados.setOnItemClickListener(this);

        dados = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dados);
        listViewReprovados.setAdapter(adapter);

        receiver = new MyBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter(ACTION_DISCHARGING);
        registerReceiver(receiver, intentFilter);
    }//onCreate

    private class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(ACTION_DISCHARGING)) {
                toast("Ação: "+ intent.getAction());
                adapter.clear();
                Secretaria.reprovados.stream().forEach(aluno -> dados.add(aluno));
                adapter.notifyDataSetChanged();
            } else
                Log.i("Log", "Ação: "+intent.getAction());
        }
    }//classe interna

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //identificar a ação de voltar a tela
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
        toast(aluno.getNome()+" reprovado kkkkkk");
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