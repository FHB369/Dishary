package com.nrick.dishary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.MotionEvent;

import java.util.Locale;

public class Slider2 extends AppCompatActivity  implements TextToSpeech.OnInitListener {

    float x1, y1, x2, y2;
    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider2);

        tts = new TextToSpeech(this, this);
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
                if(x1 > x2){
                    Intent i = new Intent(Slider2.this, Quiz1.class);
                    startActivity(i);
                    if (tts != null) {
                        tts.stop();
                        tts.shutdown();
                    }
                    overridePendingTransition(R.anim.right_to_left, R.anim.right_to_left_exit);
                    finish();
                }else if(x1<x2){
                    Intent i = new Intent(Slider2.this, Slider1.class);
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
        String text = "বাজার করতে গেলে,   আশার স্বামীর সাথে দেখা হয় গ্রামের স্বাস্থ্যকর্মী আপার সাথে।    আলি হাসান তাকে জানান মুখে অরুচির কারনে আশার এখন খুব অল্প খাবারই খেতে চায়।  " ;
        if(text!=null) {
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }
    }
}
