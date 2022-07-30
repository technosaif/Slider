package com.example.imageslider;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView mymarquee;
    Button btnstart , btnstop;
    TextToSpeech t1;

    SliderView sliderView;
    int[] images = {R.drawable.one,
    R.drawable.two,
    R.drawable.three,
    R.drawable.four,
    R.drawable.five,
    R.drawable.six};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sliderView = findViewById(R.id.image_slider);

        SliderAdapter sliderAdapter = new SliderAdapter(images);

        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);

        mymarquee = (TextView) this.findViewById(R.id.mymarquee);
        //mymarquee.setSelected(true);
        btnstart = findViewById(R.id.button);
        btnstop = findViewById(R.id.button2);

        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status!=TextToSpeech.ERROR){ t1.setLanguage(Locale.UK);
                }
            }
        });

        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mymarquee.setSelected(true);

                sliderView.startAutoCycle();

                String tospeak = mymarquee.getText().toString();
                t1.speak(tospeak,TextToSpeech.QUEUE_FLUSH,null);

            }
        });

        btnstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mymarquee.setSelected(false);
                t1.stop();
                sliderView.stopAutoCycle();

            }
        });

    }
}