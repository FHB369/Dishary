package com.nrick.dishary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.MotionEvent;

import java.util.Locale;

public class Slider3 extends AppCompatActivity  implements TextToSpeech.OnInitListener {

    float x1, x2, y1, y2;

    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider3);

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
                    Intent i = new Intent(Slider3.this, Slider4.class);
                    startActivity(i);
                    if (tts != null) {
                        tts.stop();
                        tts.shutdown();
                    }
                    overridePendingTransition(R.anim.right_to_left, R.anim.right_to_left_exit);
                    finish();
                }else if(x1<x2){
                    Intent i = new Intent(Slider3.this, Slider2.class);
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
        String text = "এমন অবস্থায় একদিন পুকুর থেকে পানি নিয়ে আসার পথে তার দিকে ছুটে আসলেন পাশের বাড়ির শরিফা বুবু।    তার কোল থেকে কলসী কেড়ে নিয়ে বললেন,   গর্ভধারনের সময় কোনোভাবেই ভারী কাজ করা যাবে না।" ;
        if(text!=null) {
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }
    }
}
