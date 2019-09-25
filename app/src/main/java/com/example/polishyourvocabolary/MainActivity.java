package com.example.polishyourvocabolary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView splash_image;
    Button startApp;
    Animation frombottom;
    Animation fromtop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startApp = findViewById(R.id.button_splash);
        splash_image = findViewById(R.id.logo_splash);
        frombottom = AnimationUtils.loadAnimation(MainActivity.this,R.anim.splash_screen_animation);
        fromtop = AnimationUtils.loadAnimation(MainActivity.this,R.anim.splash_screen_animation2);
        startApp.startAnimation(frombottom);
        splash_image.startAnimation(fromtop);
        startApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(MainActivity.this,MainScreen.class));
                MediaPlayer mediaPlayer= MediaPlayer.create(MainActivity.this,R.raw.swoosh_1);
                mediaPlayer.start();
            }
        });
    }
}
