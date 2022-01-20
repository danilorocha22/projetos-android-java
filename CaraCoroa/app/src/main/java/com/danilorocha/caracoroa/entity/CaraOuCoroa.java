package com.danilorocha.caracoroa.entity;

import android.widget.ImageView;

import androidx.core.content.ContextCompat;

import com.airbnb.lottie.LottieAnimationView;
import com.danilorocha.caracoroa.R;
import com.danilorocha.caracoroa.activity.MainActivity;

import java.util.Random;

public class CaraOuCoroa {
    private MainActivity mainActivity;

    public CaraOuCoroa(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void jogar(LottieAnimationView lottie, ImageView view) {
        int valor = random();
        switch (valor) {
            case 0:
                lottie.setImageDrawable(ContextCompat.getDrawable(mainActivity, R.drawable.cara));
                view.setImageDrawable(ContextCompat.getDrawable(mainActivity, R.drawable.logo_cara));
                break;

            case 1:
                lottie.setImageDrawable(ContextCompat.getDrawable(mainActivity, R.drawable.coroa));
                view.setImageDrawable(ContextCompat.getDrawable(mainActivity, R.drawable.logo_coroa));
                break;
        }
    }//jogar

    private int random() {
        Random random = new Random();
        return random.nextInt(2);
    }//random
}
