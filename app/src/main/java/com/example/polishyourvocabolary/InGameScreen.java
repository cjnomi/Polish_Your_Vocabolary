package com.example.polishyourvocabolary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class InGameScreen extends AppCompatActivity {

    public static TextToSpeech textToSpeech;
    Handler handler;
    Runnable runnable;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_game_screen);


        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = textToSpeech.setLanguage(Locale.US);

                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.i("TextToSpeach", "Language not supported");
                    }
                } else {
                    Log.i("TextToSpeach", "Initialization failed");
                }
            }
        });

        Dialog dialog = new Dialog(InGameScreen.this);
        dialog.setContentView(R.layout.custom_dialog_ingame);
        CircleIndicator circleIndicator = dialog.findViewById(R.id.circleindicator_id);
        final ViewPager viewPager = dialog.findViewById(R.id.view_pager);
        final SlideShowAdapter adapter = new SlideShowAdapter(this);
        viewPager.setAdapter(adapter);
        circleIndicator.setViewPager(viewPager);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.show();

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                int i  = viewPager.getCurrentItem();
                if(i==adapter.tutorial_text.length-1)
                {
                    i=0;
                    viewPager.setCurrentItem(i,true);
                }else{
                    i++;
                    viewPager.setCurrentItem(i,true);
                }
            }
        };
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        },8000,8000);

    }

    @Override
    protected  void onDestroy() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }

        super.onDestroy();
    }
}
