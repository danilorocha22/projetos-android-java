package com.danilorocha.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.SyncStateContract;
import android.provider.Telephony;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private RadioGroup radioGroup;
    private String msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.inputMsg);
        radioGroup = findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(enviarMensagem());
        editText.setOnClickListener(v -> {radioGroup.clearCheck();});
    }//onCreate

    private RadioGroup.OnCheckedChangeListener enviarMensagem() {
        return (group, checkedId) -> {
            if (verificarInput()) {
                msg = editText.getText().toString();
                Intent it = instanciarIntent();

                switch (checkedId) {
                    case R.id.whatsapp:
                        porWhatsApp(it);
                        break;

                    case R.id.gmail:
                        porGmail(it);
                        break;

                    case R.id.sms:
                        porSms(it);
                        break;
                }//switch
                editText.setText("");
                radioGroup.clearCheck();
            }//if
        };
    }//metodo

    private Intent instanciarIntent() {
        Intent it = new Intent();
        it.setAction(Intent.ACTION_SEND);//intenção da intent: enviar dados para alguém
        return it;
    }//metodo


    private void porWhatsApp(Intent it) {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.whatsapp", PackageManager.GET_META_DATA);
            it.setType("text/plain");
            it.putExtra(Intent.EXTRA_TEXT, msg);
            it.setPackage("com.whatsapp");
            startActivity(it);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            toast("Por favor instale o app do WhatsApp no seu celular");
        }
    }//metodo

    private void porGmail(Intent it) {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.google.android.gm", PackageManager.GET_META_DATA);
            it.setType("text/plain");//tipo de texto que será enviado
            it.putExtra(Intent.EXTRA_EMAIL, "danilo.rochaa@gmail.com");//definindo email
            it.putExtra(Intent.EXTRA_SUBJECT, "Olá");//definindo titulo
            it.putExtra(Intent.EXTRA_TEXT, msg);//definindo a mensagem
            it.setPackage("com.google.android.gm");//por qual app será enviado
            startActivity(it);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            toast("Por favor instale o app do Gmail no seu celular");
        }
    }//metodo

    private void porSms(Intent it) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            String pacotePadrao = Telephony.Sms.getDefaultSmsPackage(getApplicationContext());//Se a versão usado do SDK for maior que a do KITKAT usamos este metodo para pegar o pacote
            it.setType("text/plain");//tipo de texto que será enviado
            it.putExtra(Intent.EXTRA_TEXT, msg);//definindo a mensagem
            it.setPackage(pacotePadrao);
        } else {
            it.setType("vnd.android-dir/mms-sms");//tipo de texto que será enviado
            it.putExtra(Intent.EXTRA_TEXT, msg);//definindo a mensagem
        }
        startActivity(it);
    }//metodo

    private boolean verificarInput() {
        return !editText.getText().toString().isEmpty();
    }//metodo

    private void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }//metodo

}//classe