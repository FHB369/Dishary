package com.nrick.dishary;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Created by Faisal Haque Bappy on 18-Jul-19.
 */
public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.MyViewHolder> {
    Context context;
    private List<Course> coursesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, catagory, status;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            catagory = (TextView) view.findViewById(R.id.catagory);
            status = (TextView) view.findViewById(R.id.status);
        }
    }

    public CourseAdapter(Context context, List<Course> courseList) {
        this.context = context;
        this.coursesList = courseList;
    }

    @NonNull
    @Override
    public CourseAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.course_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.MyViewHolder holder, int position) {
        final Course movie = coursesList.get(position);
        holder.title.setText(movie.getTitle());
        holder.catagory.setText(movie.getCatagory());
        holder.status.setText(movie.getStatus());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(movie.getStatus().equals("শুরু করা হয়নি")) {
                    Intent intent = new Intent(context, CourseSlide.class);
                    intent.putExtra("RefTitle", movie.getTitle());
                    intent.putExtra("RefDesc", movie.getCatagory());
                    intent.putExtra("RefStatus", movie.getStatus());
                    context.startActivity(intent);
                }else{
                    Intent intent = new Intent(context, EnrolledCourseSlide.class);
                    intent.putExtra("RefTitle", movie.getTitle());
                    intent.putExtra("RefDesc", movie.getCatagory());
                    intent.putExtra("RefStatus", movie.getStatus());
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return coursesList.size();
    }
}
