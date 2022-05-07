package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StudentCoursePopUp extends AppCompatActivity {
    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_course_pop_up);

        //ImageView image = (ImageView)findViewById(R.id.image);
        TextView course_name = (TextView)findViewById(R.id.course_name);
        TextView course_description = (TextView)findViewById(R.id.course_description);
        TextView category = (TextView)findViewById(R.id.category);
        RatingBar ratingbar = (RatingBar)findViewById(R.id.ratingBar) ;
        //TextView lecturer_name = (TextView)findViewById(R.id.lecturer_name);

        Intent intent = getIntent();

        Bundle extras = intent.getExtras();
        course_name.setText(extras.getString("courseName"));
        course_description.setText("Description: " + extras.getString("courseDescription"));
        category.setText(extras.getString("category"));
        ratingbar.setRating(Float.parseFloat(extras.getString("rating")));
        //lecturer_name.setText(bundle.getString("lecturerName"));

    }
}
