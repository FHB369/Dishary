package com.nrick.dishary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Locale;

public class Quiz1 extends AppCompatActivity implements TextToSpeech.OnInitListener{
    float x1,x2,y1,y2;
    private Button opt1, opt2, opt3;
    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz1);

        tts = new TextToSpeech(this, this);

        opt1 = findViewById(R.id.button);
        opt2 = findViewById(R.id.button2);
        opt3 = findViewById(R.id.button3);

        opt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "অভিনন্দন। আপনার উত্তর সঠিক হয়েছে", Toast.LENGTH_LONG).show();

                Intent i = new Intent(Quiz1.this, Slider3.class);
                startActivity(i);
                if (tts != null) {
                    tts.stop();
                    tts.shutdown();
                }
                overridePendingTransition(R.anim.right_to_left, R.anim.right_to_left_exit);
                finish();
            }
        });

        opt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "আপনার উত্তর সঠিক হয়নি। আবার চেষ্টা করুন।", Toast.LENGTH_LONG).show();
            }
        });

        opt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "আপনার উত্তর সঠিক হয়নি। আবার চেষ্টা করুন।", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        speakOut();
    }

    public boolean onTouchEvent(MotionEvent touchEvent){
        switch(touchEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1 = touchEvent.getX();
                y1 = touchEvent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchEvent.getX();
                y2 = touchEvent.getY();
                if(x1<x2){
                    Intent i = new Intent(Quiz1.this, Slider2.class);
                    startActivity(i);
                    if (tts != null) {
                        tts.stop();
                        tts.shutdown();
                    }
                    overridePendingTransition(R.anim.left_to_right_exit, R.anim.left_to_right);
                    finish();
                }
                break;
        }
        return false;
    }

    @Override
    public void onDestroy() {
        // Don't forget to shutdown tts!
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    @Override
    public void onInit(int status) {

        if (status == TextToSpeech.SUCCESS) {

            int result = tts.setLanguage(new Locale("bn_BD"));

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {
                speakOut();
            }

        } else {
            Log.e("TTS", "Initilization Failed!");
        }

    }

    private void speakOut() {
        String text = "আসুন দেখি এই পাঠ থেকে কি শিখলেন।   পাশের প্রশ্নটি পড়ুন এবং সঠিক উত্তরের উপত টেপ করুন" ;
        if(text!=null) {
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }
    }
}
