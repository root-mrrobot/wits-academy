package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class displayQuizResults extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_quiz_results);

        Intent takeQuizIntent = getIntent();
        String score = takeQuizIntent.getStringExtra("scoreString");

        Button done = findViewById(R.id.btn_doneReview);
        TextView scoreText = findViewById(R.id.txt_score);

        scoreText.setText(score + "%");

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(displayQuizResults.this, QuizzesList.class);
                startActivity(intent);
            }
        });

    }
}