package com.danilorocha.app.activity;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.danilorocha.app.R;
import com.danilorocha.app.model.Agenda;
import com.danilorocha.app.util.MyBroadcast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private CountDownTimer temporizador;
    private Button btnSelecionarTempo, btnAgendar, btnCancelar;
    private ArrayAdapter<CharSequence> adapter;
    private Spinner spinner;
    private String disciplina;
    private Integer horas = 0, minutos = 0, segundos = 0;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private static final String CANAL_ID = "2";
    private long tempoMilisegundos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = findViewById(R.id.spinnerDisciplina);
        btnAgendar = findViewById(R.id.btnAgendar);
        btnCancelar = findViewById(R.id.btnCancelar);
        btnSelecionarTempo = findViewById(R.id.btnAlarme);
        adapter = ArrayAdapter.createFromResource(this, R.array.disciplinas, R.layout.meu_spinner_item);
        adapter.setDropDownViewResource(R.layout.meu_spinner_dropdown_itens);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(pegarDisciplina());
        criarCanal();
    }//onCreate

    private AdapterView.OnItemSelectedListener pegarDisciplina() {
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                disciplina = spinner.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        };
    }//metodo

    public void timePickerDialog(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int selecionarHora, int selecionarMinuto) {
                horas = Integer.valueOf(selecionarHora);
                minutos = Integer.valueOf(selecionarMinuto);
                btnSelecionarTempo.setText(String.format(Locale.getDefault(), "%02d:%02d:%02d", horas, minutos, segundos));
            }//metodo
        };
        int style = AlertDialog.THEME_HOLO_DARK;
        TimePickerDialog pickerDialog = new TimePickerDialog(this, style, onTimeSetListener, horas, minutos, true);
        pickerDialog.setTitle("ALARME");
        pickerDialog.show();
    }//metodo

    public void agendar(View view) {
        String sucesso = Agenda.salvar(disciplina, horas, minutos);
        toast(sucesso);
        if (sucesso.equals("Agendado com sucesso!")) {
            marcarAlarme();
            iniciarTemporizador();
            btnAgendar.setVisibility(View.INVISIBLE);
            btnCancelar.setVisibility(View.VISIBLE);
            horas = 0;
            minutos = 0;
        }
    }//metodo

    private void marcarAlarme() {
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent i = new Intent(this, MyBroadcast.class);
        i.putExtra("id_canal", CANAL_ID);
        pendingIntent = PendingIntent.getBroadcast(this, 0, i, 0);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() + Agenda.getTempo() * 60000, pendingIntent);
    }//metodo

    private void iniciarTemporizador() {
        temporizador = new CountDownTimer(Agenda.getTempo() * 60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tempoMilisegundos = millisUntilFinished;
                atualizarTemporizador();
            }
            @Override
            public void onFinish() {}
        }.start();
    }//metodo

    private void atualizarTemporizador() {
        int horas = (int) tempoMilisegundos / 3600000;
        int minutos = (horas == 0) ? (int) (tempoMilisegundos / 1000) / 60 : (int) ((tempoMilisegundos - 3600000) / 1000) / 60;
        int segundos = (int) (tempoMilisegundos / 1000) % 60;
        String txtTemporizadorFormatado = String.format(Locale.getDefault(), "%02d:%02d:%02d", horas, minutos, segundos);
        btnSelecionarTempo.setText(txtTemporizadorFormatado);
    }//metodo

    private void criarCanal() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {//verifica as versões do Android
            CharSequence nome = "questionário";
            String descricao = "questionário de disciplinas";
            int importancia = NotificationManager.IMPORTANCE_DEFAULT;//define a prioridade
            NotificationChannel canal = new NotificationChannel(CANAL_ID, nome, importancia);//cria o objeto canal
            canal.setDescription(descricao);
            //registrar o canal no sistema
            NotificationManager nm = getSystemService(NotificationManager.class);
            nm.createNotificationChannel(canal);
        }
    }//metodo

    public void cancelar(View view) {
        horas = 0;
        minutos = 0;
        alarmManager.cancel(pendingIntent);
        temporizador.cancel();
        btnCancelar.setVisibility(View.INVISIBLE);
        btnAgendar.setVisibility(View.VISIBLE);
        spinner.cancelDragAndDrop();
        btnSelecionarTempo.setText("ALARME");
        toast("Alarme cancelado");
    }//metodo

    private void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }//metodo

}//classe