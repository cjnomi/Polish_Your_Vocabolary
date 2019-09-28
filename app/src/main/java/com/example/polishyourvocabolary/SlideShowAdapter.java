package com.example.polishyourvocabolary;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;


public class SlideShowAdapter extends PagerAdapter{

    private Context context;
    LayoutInflater inflater;

    public int[] tutorial_text={
            R.string.tutorial_text_1,
            R.string.tutorial_text_2,
            R.string.tutorial_text_3,
            R.string.tutorial_text_4,
            R.string.tutorial_text_5,
            R.string.tutorial_text_6,
            R.string.tutorial_text_7,
    };

    public SlideShowAdapter(Context context)
    {
        this.context = context;

    }
    @Override
    public int getCount() {
        return tutorial_text.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view==(LinearLayout)object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.slide_show_layout,container,false);
        final TextView textView = view.findViewById(R.id.slide_text);




        textView.setText(tutorial_text[position]);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    float pitch = 1.0f;
                    float speed = 1.0f;
                    InGameScreen.textToSpeech.setPitch(pitch);
                    InGameScreen.textToSpeech.setSpeechRate(speed);
                    String text = textView.getText().toString();
                    InGameScreen.textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
                }catch (Exception ex)
                {
                    Log.i("toast",ex.getMessage());
                }
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }
}
