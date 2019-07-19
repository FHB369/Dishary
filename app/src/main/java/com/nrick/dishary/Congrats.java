package com.nrick.dishary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Congrats extends AppCompatActivity {

    private Button goHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congrats);

        goHome = findViewById(R.id.button7);

        goHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Congrats.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences.Editor editor = getSharedPreferences("course", MODE_PRIVATE).edit();
        editor.putString("status", "enrolled");
        editor.apply();

        SharedPreferences.Editor editor1 = getSharedPreferences("point", MODE_PRIVATE).edit();
        editor1.putInt("point", 10);
        editor1.apply();
    }
}
