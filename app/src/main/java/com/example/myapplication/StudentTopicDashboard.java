package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StudentTopicDashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_topic_dashboard);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        Button resources = findViewById(R.id.btn_resources);
        resources.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                System.out.println("resources clicked");
                Intent intent = new Intent(getApplicationContext(), TopicResources.class);
                startActivity(intent);
            }
        });

        Button quizzes = findViewById(R.id.btn_quizzes);
        quizzes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                System.out.println("quizzes clicked");
                Intent intent = new Intent(getApplicationContext(), QuizzesList.class);
                startActivity(intent);
            }
        });

    }
}