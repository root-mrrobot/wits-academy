package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class StudentCoursePopUp extends AppCompatActivity {
    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_course_pop_up);

        // initialize all xml objects that appear on the pop up page
        ImageView image = (ImageView)findViewById(R.id.image);
        TextView course_name = (TextView)findViewById(R.id.course_name);
        TextView course_description = (TextView)findViewById(R.id.course_description);
        TextView category = (TextView)findViewById(R.id.category);
        RatingBar ratingbar = (RatingBar)findViewById(R.id.ratingBar) ;
        TextView lecturer_name = (TextView)findViewById(R.id.lecturer_name);

        // initialize a getIntent
        Intent intent = getIntent();
        // initialize a bundle called extras to retrieve information from student home fragment
        Bundle extras = intent.getExtras();

        // set all the objects to the matching information that was retrieved using the getExtras method
        course_name.setText(extras.getString("courseName"));
        course_description.setText("Description: " + extras.getString("courseDescription"));
        category.setText(extras.getString("category"));
        ratingbar.setRating(Float.parseFloat(extras.getString("rating")));
        lecturer_name.setText(extras.getString("lecturerName"));
        Glide.with(getApplicationContext()).load(extras.getString("image")).into(image);

        // TODO: onClickListener for the exit button and the subscribe button(Sprint 3)

    }
}
