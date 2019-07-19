package com.nrick.dishary;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AllCourse extends AppCompatActivity {
    private TextView mTextMessage;

    private List<Course> movieList = new ArrayList<>();
    private List<Course> movieList2 = new ArrayList<>();
    private RecyclerView recyclerView;
    private CourseAdapter aAdapter, cAdapter;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    recyclerView.setAdapter(aAdapter);
                    return true;
                case R.id.navigation_notifications:
                    recyclerView.setAdapter(cAdapter);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_course);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        recyclerView = (RecyclerView) findViewById(R.id.course_frame);

        aAdapter = new CourseAdapter(this, movieList);
        cAdapter = new CourseAdapter(this, movieList2);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(aAdapter);

        prepareMovieData();
    }

    private void prepareMovieData() {
        SharedPreferences prefs = getSharedPreferences("course", MODE_PRIVATE);
        String status = prefs.getString("status", null);
        if (status != null) {
            if(status.equals("enrolled")){
                Course movieT = new Course("গর্ভবতী মায়ের স্বাস্থ্য", "এই পাঠে আমরা সমন্বয় করেছি গর্ভবতী মায়ের শারীরিক পুষ্টি, মানসিক যত্ন, সতর্কতা এবং বিপদ চিনহ বিষয়ক তথ্যাদি", "শেষ করা হয়েছে");
                movieList2.add(movieT);
            }}else{
                Course movie = new Course("গর্ভবতী মায়ের স্বাস্থ্য", "এই পাঠে আমরা সমন্বয় করেছি গর্ভবতী মায়ের শারীরিক পুষ্টি, মানসিক যত্ন, সতর্কতা এবং বিপদ চিনহ বিষয়ক তথ্যাদি", "শুরু করা হয়নি");
                movieList.add(movie);
            }


        String status1 = prefs.getString("status1", null);
        if (status1 != null) {
            if(status1.equals("enrolled")){
                Course movieT = new Course("বয়ঃসন্ধিকাল", "এই পাঠে আলোচনা হয়েছে বয়ঃসন্ধির গুরুত্বপূর্ণ সময়ে কিশোরকিশোরীদের মানসিকতার পরিবর্তন ও দৈহিক বিভিন্ন সমস্যা", "শুরু করা হয়েছে");
                movieList2.add(movieT);
            }}else{
                Course movie = new Course("বয়ঃসন্ধিকাল", "এই পাঠে আলোচনা হয়েছে বয়ঃসন্ধির গুরুত্বপূর্ণ সময়ে কিশোরকিশোরীদের মানসিকতার পরিবর্তন ও দৈহিক বিভিন্ন সমস্যা", "শুরু করা হয়নি");
                movieList.add(movie);
            }


        String status2 = prefs.getString("status2", null);
        if (status2 != null) {
            if(status2.equals("enrolled")){
                Course movieT = new Course("কিশোরী স্বাস্থ্য", "এ অংশে বিশেষভাবে গুরুত্ব দেয়া হয়েছে মাসিক সঙ্ক্রান্ত পরিচ্ছন্নতা, সমস্যা ও সমাধান এর উপর", "শুরু করা হয়েছে");
                movieList2.add(movieT);
            }}else{
                Course movie = new Course("কিশোরী স্বাস্থ্য", "এ অংশে বিশেষভাবে গুরুত্ব দেয়া হয়েছে মাসিক সঙ্ক্রান্ত পরিচ্ছন্নতা, সমস্যা ও সমাধান এর উপর", "শুরু করা হয়নি");
                movieList.add(movie);
            }


        String status3 = prefs.getString("status3", null);
        if (status3 != null) {
            if(status3.equals("enrolled")){
                Course movieT = new Course("পরিষ্কার পরিচ্ছন্নতা", "মৌলিক পরিচ্ছন্নতা ও সুস্বাস্থ্যের নিয়মকানুন সম্পর্কে জানবেন এখানে", "শুরু করা হয়েছে");
                movieList2.add(movieT);
            }}else{
                Course movie = new Course("পরিষ্কার পরিচ্ছন্নতা", "মৌলিক পরিচ্ছন্নতা ও সুস্বাস্থ্যের নিয়মকানুন সম্পর্কে জানবেন এখানে", "শুরু করা হয়নি");
                movieList.add(movie);
            }


        String status4 = prefs.getString("status4", null);
        if (status4 != null) {
            if(status4.equals("enrolled")){
                Course movieT = new Course("পরিবার পরিকল্পনা", "পরিবার ছোট, পরিকল্পিত ও সুন্দর রাখা সঙ্ক্রান্ত তথ্যাদি পাবেন এ অংশে", "শুরু করা হয়েছে");
                movieList2.add(movieT);
            }}else{
                Course movie = new Course("পরিবার পরিকল্পনা", "পরিবার ছোট, পরিকল্পিত ও সুন্দর রাখা সঙ্ক্রান্ত তথ্যাদি পাবেন এ অংশে", "শুরু করা হয়নি");
                movieList.add(movie);
            }

        aAdapter.notifyDataSetChanged();
        cAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        movieList.clear();
        movieList2.clear();
        prepareMovieData();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_to_right_exit, R.anim.left_to_right);
    }
}
