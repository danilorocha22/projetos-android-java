package com.danilorocha.app.service;

import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.Nullable;

public class Service1 extends IntentService {
    /**
     * Creates an IntentService. Invoked by your subclass's constructor.
     **/
    public Service1() {
        super("Serviço 1");
    }//construtor

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        System.out.println("Serviço 1 iniciado");
    }//metodo

}//classe
