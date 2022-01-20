package com.danilorocha.app;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.Locale;

public class SegundaActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {
    private Button play, pause, stop;
    private VideoView videoView;
    private TextToSpeech textToSpeech;
    private Uri uri;
    private String msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);
        play = findViewById(R.id.buttonPlay);
        pause = findViewById(R.id.buttonPause);
        stop = findViewById(R.id.buttonStop);
        videoView = findViewById(R.id.videoView);

        String nome = getIntent().getStringExtra("nome");
        msg = nome.concat(" seu vídeo está pronto");

        textToSpeech = new TextToSpeech(this, this);
        //uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.corinthians_1_x_0_athletico_pr);
        String path = "android.resource://"+getPackageName()+"/"+R.raw.corinthians_1_x_0_athletico_pr;
        //videoView.setVideoURI(uri);
        videoView.setVideoPath(path);
    }//onCreate

    public void click(View view) {
        switch (view.getId()) {
            case R.id.buttonPlay:
                int duracao = videoView.getDuration();
                toast("Tempo total "+ duracao);
                videoView.start();
                break;

            case R.id.buttonPause:
                videoView.pause();
                int posicao = videoView.getCurrentPosition();
                toast("Tempo atual "+ posicao);
                break;

            case R.id.buttonStop:
                videoView.stopPlayback();
                toast("Video não pode ser mais reproduzido");
                finish();
                break;
        }//switch
    }//metodo

    @Override
    public void onInit(int status) {
        if  (status == TextToSpeech.SUCCESS) {
            Locale locale = new Locale("pt", "br");
            int result = textToSpeech.setLanguage(locale);
            textToSpeech.setSpeechRate(1f);
            if (result == TextToSpeech.LANG_MISSING_DATA ||
                result == textToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("problemasI", "Problema com idioma, não é suportado");
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    textToSpeech.speak(msg, TextToSpeech.QUEUE_FLUSH, null, null);
                } else {
                    textToSpeech.speak(msg, TextToSpeech.QUEUE_FLUSH, null);
                }//else
            }//else
        } else {
            Log.e("problemasT", "Problema com textToSpeech");
        }
    }//metodo

    private void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }//metodo

}//classe