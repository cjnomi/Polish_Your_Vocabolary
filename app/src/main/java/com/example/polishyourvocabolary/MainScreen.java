package com.example.polishyourvocabolary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainScreen extends AppCompatActivity {

    ImageView play_button;
    TextView gamename,gamename2;
    Typeface typeface,typeface2;
    Animation image_anim;
    String level_indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        gamename = findViewById(R.id.textview_polish);
        gamename2 = findViewById(R.id.textview_Vocabulary);
        play_button = findViewById(R.id.play_image);
        typeface2 = ResourcesCompat.getFont(MainScreen.this,R.font.lobster_two_italic);
        gamename2.setTypeface(typeface2);
        typeface = ResourcesCompat.getFont(MainScreen.this,R.font.pacifico);
        gamename.setTypeface(typeface);
        image_anim = AnimationUtils.loadAnimation(MainScreen.this,R.anim.bounce);
        play_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer mediaPlayer = MediaPlayer.create(MainScreen.this,R.raw.swoosh_2);
                mediaPlayer.start();
                play_button.startAnimation(image_anim);
                final Dialog dialog = new Dialog(MainScreen.this);
                dialog.setContentView(R.layout.custom_dialog_levels);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                dialog.show();
                dialog.setCancelable(false);
                final Button beginner = dialog.findViewById(R.id.beginner);
                final Button intermediate = dialog.findViewById(R.id.inter);
                final Button expert = dialog.findViewById(R.id.expert);
                final TextView tip = dialog.findViewById(R.id.tip);
                Button cancel = dialog.findViewById(R.id.cancel_button);
                Button ok = dialog.findViewById(R.id.ok_button);
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                        startActivity(new Intent(MainScreen.this,InGameScreen.class));

                    }
                });
                beginner.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        beginner.setBackgroundResource(R.drawable.difficulti_round_button_purple);
                        beginner.setTextColor(getResources().getColor(R.color.white));

                        level_indicator = beginner.getText().toString();

                        tip.setText("You are about to write 20 words in 15 minutes in beginner level");

                        intermediate.setBackgroundResource(R.drawable.difficulti_round_button_white);
                        intermediate.setTextColor(getResources().getColor(R.color.black));

                        expert.setBackgroundResource(R.drawable.difficulti_round_button_white);
                        expert.setTextColor(getResources().getColor(R.color.black));
                    }
                });

                intermediate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        beginner.setBackgroundResource(R.drawable.difficulti_round_button_white);
                        beginner.setTextColor(getResources().getColor(R.color.black));

                        level_indicator = intermediate.getText().toString();

                        tip.setText("You are about to write 30 words in 20 minutes in intermediate level");

                        intermediate.setBackgroundResource(R.drawable.difficulti_round_button_purple);
                        intermediate.setTextColor(getResources().getColor(R.color.white));

                        expert.setBackgroundResource(R.drawable.difficulti_round_button_white);
                        expert.setTextColor(getResources().getColor(R.color.black));
                    }
                });

                expert.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        beginner.setBackgroundResource(R.drawable.difficulti_round_button_white);
                        beginner.setTextColor(getResources().getColor(R.color.black));

                        level_indicator = expert.getText().toString();

                        tip.setText("You are about to write 50 words in 25 minutes in expert level");

                        intermediate.setBackgroundResource(R.drawable.difficulti_round_button_white);
                        intermediate.setTextColor(getResources().getColor(R.color.black));

                        expert.setBackgroundResource(R.drawable.difficulti_round_button_red);
                        expert.setTextColor(getResources().getColor(R.color.white));
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });


            }
        });
    }
}
