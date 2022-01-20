package com.danilorocha.app.util;

import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.danilorocha.app.R;
import com.danilorocha.app.activity.QuestionarioActivity;
import com.danilorocha.app.model.Agenda;

public class MyBroadcast extends BroadcastReceiver {
    private static final int NOTIFICACAO_ID = 1;   //ID da notificação

    @Override
    public void onReceive(Context context, Intent intent) {
        String idCanal = intent.getStringExtra("id_canal");
        gerarNotificacao(context, idCanal);
    }//onReceive

    private void gerarNotificacao(Context context, String idCanal) {
        //cria um intent para iniciar outra atividade
        Intent resultIntent = new Intent(context, QuestionarioActivity.class);
        //cria uma TaskStackBuilder para add o intent, inflando o retorno da pilha
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addNextIntentWithParentStack(resultIntent);
        //pega o PendingIntent que contem o retorno da pilha
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, idCanal)//o construtor precisa do contexto e o ID do canal
                .setSmallIcon(R.drawable.pngwing)//define a icone da notificação
                .setTicker("Hora do Questionário")
                .setContentTitle(Agenda.getDisciplina())//define o título da notificação
                .setContentText("Responder questões")//define o conteúdo da notificação
                .setContentIntent(resultPendingIntent)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);//a prioridade
        NotificationManagerCompat nm = NotificationManagerCompat.from(context);
        nm.notify(NOTIFICACAO_ID, builder.build());//para exibir a notificação
    }//metodo

}//classe