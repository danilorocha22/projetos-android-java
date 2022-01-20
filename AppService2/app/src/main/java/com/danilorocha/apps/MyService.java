package com.danilorocha.apps;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyService extends Service {
    private Looper serviceLooper;
    private ServiceHandler serviceHandler;

    private final class ServiceHandler extends Handler {
        public ServiceHandler(@NonNull Looper looper) {
            super(looper);
        }//construtor

        @Override
        public void handleMessage(@NonNull Message msg) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }//try/catch
            stopSelf(msg.arg1);
        }//metodo
    }//classe interna

    @Override
    public void onCreate() {
        HandlerThread thread = new HandlerThread("Argumentos do Serviço",
                Process.THREAD_PRIORITY_BACKGROUND);
        thread.start();
        serviceLooper = thread.getLooper();
        serviceHandler = new ServiceHandler(serviceLooper);
    }//metodo

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Serviço iniciado com Service...", Toast.LENGTH_SHORT).show();
        Message msg = serviceHandler.obtainMessage();
        msg.arg1 = startId;
        serviceHandler.sendMessage(msg);
        return START_STICKY;
    }//metodo

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }//metodo

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Serviço feito", Toast.LENGTH_SHORT).show();
    }//metodo

}//classe
