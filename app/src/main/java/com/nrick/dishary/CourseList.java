package com.nrick.dishary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class CourseList extends AppCompatActivity {

    private List<Course> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private CourseAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);

        recyclerView = (RecyclerView) findViewById(R.id.recycler);

        mAdapter = new CourseAdapter(movieList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareMovieData();
    }

    private void prepareMovieData() {
        Course movie = new Course("Mad Max: Fury Road", "Action & Adventure", "2015");
        movieList.add(movie);

        movie = new Course("Inside Out", "Animation, Kids & Family", "2015");
        movieList.add(movie);

        movie = new Course("Star Wars: Episode VII - The Force Awakens", "Action", "2015");
        movieList.add(movie);

        movie = new Course("Shaun the Sheep", "Animation", "2015");
        movieList.add(movie);

        movie = new Course("The Martian", "Science Fiction & Fantasy", "2015");
        movieList.add(movie);

        movie = new Course("Mission: Impossible Rogue Nation", "Action", "2015");
        movieList.add(movie);

        movie = new Course("Up", "Animation", "2009");
        movieList.add(movie);

        movie = new Course("Star Trek", "Science Fiction", "2009");
        movieList.add(movie);

        movie = new Course("The LEGO Movie", "Animation", "2014");
        movieList.add(movie);

        movie = new Course("Iron Man", "Action & Adventure", "2008");
        movieList.add(movie);

        movie = new Course("Aliens", "Science Fiction", "1986");
        movieList.add(movie);

        movie = new Course("Chicken Run", "Animation", "2000");
        movieList.add(movie);

        movie = new Course("Back to the Future", "Science Fiction", "1985");
        movieList.add(movie);

        movie = new Course("Raiders of the Lost Ark", "Action & Adventure", "1981");
        movieList.add(movie);

        movie = new Course("Goldfinger", "Action & Adventure", "1965");
        movieList.add(movie);

        movie = new Course("Guardians of the Galaxy", "Science Fiction & Fantasy", "2014");
        movieList.add(movie);

        mAdapter.notifyDataSetChanged();
    }
}
