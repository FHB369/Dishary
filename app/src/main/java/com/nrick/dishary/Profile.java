package com.nrick.dishary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

    private TextView name, email;
    private Button logOut;

    private static final String MY_PREFS_NAME = new String("AUTH");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name = findViewById(R.id.profile_name);
        email = findViewById(R.id.profile_email);
        logOut = findViewById(R.id.log_out);

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String userId = prefs.getString("id", null);
        String name = prefs.getString("name", null);
        if (userId != null) {
            email.setText("BRAC ID: "+userId);
        }
        if(name != null){
            this.name.setText(name);
        }

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putString("id", null);
                editor.putString("name", null);
                editor.putString("email", null);
                editor.putString("phone", null);
                editor.apply();

                Intent intent = new Intent(Profile.this, Login.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
