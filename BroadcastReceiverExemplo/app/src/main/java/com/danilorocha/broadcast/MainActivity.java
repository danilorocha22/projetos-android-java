package com.danilorocha.broadcast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Button buttonGerar, buttonDisparar;
    private ListView listViewNumeros;
    private ArrayAdapter<Integer> adapter;
    private ArrayList<Integer> dados;
    private int numeroAleatorio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonGerar = findViewById(R.id.btnGerarNum);
        buttonDisparar = findViewById(R.id.btnDisparar);
        listViewNumeros = findViewById(R.id.listViewNumeros);

        dados = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dados);
        listViewNumeros.setAdapter(adapter);

        MyBroadcastReceiver myBroadcastReceiver = new MyBroadcastReceiver(); //objeto da classe
        IntentFilter intentFilter = new IntentFilter();  //cria uma IntentFilter
        intentFilter.addAction("com.danilorocha.broadcast.MY_INTENT"); // defini as ações de transmissão.
        registerReceiver(myBroadcastReceiver,intentFilter); //registra a classe que irá receber a mensagem do transmissor específico.

    }//onCreate

    public void clicar(View v){
        switch (v.getId()){
            case R.id.btnGerarNum:
                Random random = new Random();
                numeroAleatorio = random.nextInt(10);
                System.out.println(numeroAleatorio);
                Toast.makeText(this,"número gerado", Toast.LENGTH_SHORT).show();
                break;
                
            case R.id.btnDisparar:
                dispararEvento();
                break;
        }//switch
    }//clicar

    /**
     * Conteúdo abordado na aula - Broadcast Receiver
     */
    private void dispararEvento() {
        Intent intent = new Intent();  //cria-se uma Intent
        intent.putExtra("numero",numeroAleatorio);  //adiciona-se a informação a ser passada
        System.out.println("metodo disparar: "+numeroAleatorio);
        intent.setAction("com.danilorocha.broadcast.MY_INTENT"); //especifica-se o destinatário que recebe este dado extra
        sendBroadcast(intent); //envia a mensagem
    }//metodo

    private class MyBroadcastReceiver extends BroadcastReceiver { //para tratar a mensagem, é necessário estender a classe BroadcastReceiver, pois assim, terá acesso ao método onReceive.
        @Override
        public void onReceive(Context context, Intent intent) {  //método capaz de tratar a mensagem disparada
            int num = intent.getIntExtra("numero",0);  //obter o dado extra vindo da Intent
            System.out.println("classe broadcast: "+ num);
            dados.add(num); //adiciona o número no ArrayList
            adapter.notifyDataSetChanged(); //altera o adapter que preenche a ListView
        }//metodo
    }//inner class

}//classe