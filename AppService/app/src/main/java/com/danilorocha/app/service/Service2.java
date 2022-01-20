package com.danilorocha.app.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class Service2 extends Service {


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }//metodo

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //return super.onStartCommand(intent, flags, startId);
        System.out.println("Serviço começando...");
        return START_NOT_STICKY;
    }
}//classe
