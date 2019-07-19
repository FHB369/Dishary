package com.nrick.dishary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Console;

public class Login extends AppCompatActivity {

    private EditText id, password;
    private Button loginBtn;
    private ProgressBar progressBar;

    private static final String MY_PREFS_NAME = new String("AUTH");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        id = findViewById(R.id.id);
        password = findViewById(R.id.password);
        loginBtn = findViewById(R.id.login);
        progressBar = findViewById(R.id.progressBar);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String skId = id.getText().toString().trim();
                String pass = password.getText().toString().trim();

                if(skId.isEmpty()){
                    id.setError("আপনার ব্র্যাক আইডি দিন");
                }else if(pass.isEmpty()){
                    password.setError("আপনার পাসওয়ার্ড দিন");
                }else{
                    loginBtn.setVisibility(View.GONE);
                    progressBar.setVisibility(View.VISIBLE);

                    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                    String url ="https://rueful-bracket.000webhostapp.com/api/sks/"+skId+"/"+pass;

                    JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                            new Response.Listener<JSONArray>() {
                                @Override
                                public void onResponse(JSONArray response) {
                                    try {
                                        if(response.length()!=0) {
                                            JSONObject res = (JSONObject) response.get(0);
                                            String name = res.getString("name");
                                            Toast.makeText(getApplicationContext(), name, Toast.LENGTH_SHORT).show();
                                            loginBtn.setVisibility(View.VISIBLE);
                                            progressBar.setVisibility(View.GONE);

                                            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                                            editor.putString("id", res.getString("id"));
                                            editor.putString("name", res.getString("name"));
                                            editor.putString("email", res.getString("email"));
                                            editor.putString("phone", res.getString("phone"));
                                            editor.apply();

                                            Intent intent = new Intent(Login.this, MainActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }else{
                                            Toast.makeText(getApplicationContext(), "কোন ইউজার পাওয়া যায়নি", Toast.LENGTH_SHORT).show();
                                            loginBtn.setVisibility(View.VISIBLE);
                                            progressBar.setVisibility(View.GONE);
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), "লগইন করা যায়নি। আবার চেষ্টা করুণ", Toast.LENGTH_SHORT).show();
                            loginBtn.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.GONE);
                        }
                    });

                    queue.add(request);
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String userId = prefs.getString("id", null);
        if (userId != null) {
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
