package com.example.android.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView skywarn = findViewById(R.id.splashSkywarn);
        Animation fade = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade);
        skywarn.startAnimation(fade);

        Thread Timer = new Thread(){

            @Override
            public void run(){
                try {
                    sleep(7500);
                    Intent startProgram = new Intent(getApplicationContext(),WelcomeActivity.class);
                    startActivity(startProgram);
                    finish();
                    super.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Timer.start();
    }
}
