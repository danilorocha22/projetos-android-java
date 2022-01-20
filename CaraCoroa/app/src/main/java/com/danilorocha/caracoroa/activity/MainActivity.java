package com.danilorocha.caracoroa.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.danilorocha.caracoroa.R;
import com.danilorocha.caracoroa.entity.CaraOuCoroa;

public class MainActivity extends AppCompatActivity {
    private LottieAnimationView lottie;
    private ImageView view;
    private CaraOuCoroa caraOuCoroa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lottie = findViewById(R.id.lancarMoeda);
        view = findViewById(R.id.imgViewNome);
        caraOuCoroa = new CaraOuCoroa(this);
    }//onCreate

    public void lancarMoeda(View v) {
        view.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.white));
        lottie.setAnimation(R.raw.lancar);
        lottie.playAnimation();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                caraOuCoroa.jogar(lottie, view);
            }
        }, 1100);
    }//click

}//classe