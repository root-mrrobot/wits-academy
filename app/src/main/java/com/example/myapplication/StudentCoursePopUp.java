package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
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
        //TextView lecturer_name = (TextView)findViewById(R.id.lecturer_name);

        Intent intent = getIntent();

        Bundle extras = intent.getExtras();
        course_name.setText(extras.getString("courseName"));
        course_description.setText(extras.getString("courseDescription"));
        category.setText(extras.getString("category"));
        //lecturer_name.setText(bundle.getString("lecturerName"));

    }

}
