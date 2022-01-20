package com.danilorocha.app;

import android.app.IntentService;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyIntentService extends IntentService {
    /**
     * Creates an IntentService. Invoked by your subclass's constructor.
     */
    public MyIntentService() {
        super("My IntentService");
    }//construtor

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }//catch
    }//metodo

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Iniciando serviço com IntentService...",
                Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent,flags,startId);
    }//metodo

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Serviço finalizado", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }//metodo

}//classe interna