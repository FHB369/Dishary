package com.nrick.dishary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CourseSlide extends AppCompatActivity {

    private TextView title, status;
    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_slide);

        Intent intent = getIntent();
        final String Title = intent.getStringExtra("RefTitle");
        String Description = intent.getStringExtra("RefDesc");
        String Status = intent.getStringExtra("RefStatus");

        title = findViewById(R.id.textView4);
        status = findViewById(R.id.textView5);
        startButton = findViewById(R.id.startButton);

        title.setText(Title);
        status.setText(Description);


        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = getSharedPreferences("course", MODE_PRIVATE).edit();
                if(Title.equals("গর্ভবতী মায়ের স্বাস্থ্য")) {
                    Intent intent=new Intent(CourseSlide.this, Slider1.class);
                    startActivity(intent);
                    finish();
                }else if(Title.equals("বয়ঃসন্ধিকাল")){
                    editor.putString("status1", null);
                }else if(Title.equals("কিশোরী স্বাস্থ্য")){
                    editor.putString("status2", null);
                }else if(Title.equals("পরিষ্কার পরিচ্ছন্নতা")){
                    editor.putString("status3", null);
                }else if(Title.equals("পরিবার পরিকল্পনা")){
                    editor.putString("status4", null);
                }
                editor.apply();


            }
        });
    }
}
