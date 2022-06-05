package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;

public class CreateQuiz extends AppCompatActivity {

    private Button createQuiz;
    private Button addQuestion;

    private ArrayList<ArrayList<String>> questions = new ArrayList<ArrayList<String>>();
    TextView questionNumTxt;
    EditText quizNameBox;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quiz);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        quizNameBox = (EditText) findViewById(R.id.edtxt_quizName);


        EditText questionBox = (EditText) findViewById(R.id.edtxt_question);
        EditText optionABox = (EditText) findViewById(R.id.edtxt_optionA);
        EditText optionBBox = (EditText) findViewById(R.id.edtxt_optionB);
        EditText optionCBox = (EditText) findViewById(R.id.edtxt_optionC);
        EditText optionDBox = (EditText) findViewById(R.id.edtxt_optionD);
        EditText answerBox = (EditText) findViewById(R.id.edtxt_answer);
        questionNumTxt = findViewById(R.id.txt_questionNum);

        final int[] questionCounter = {1};

        addQuestion = findViewById(R.id.btn_addQuestion);
        addQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                System.out.println("button reached");
                String question = questionBox.getText().toString();
                String optionA = optionABox.getText().toString();
                String optionB = optionBBox.getText().toString();
                String optionC = optionCBox.getText().toString();
                String optionD = optionDBox.getText().toString();
                String answer = answerBox.getText().toString();

                ArrayList<String> currentQuestion = new ArrayList<>();
                currentQuestion.add(question);
                currentQuestion.add(optionA);
                currentQuestion.add(optionB);
                currentQuestion.add(optionC);
                currentQuestion.add(optionD);
                currentQuestion.add(answer);

                questions.add(currentQuestion);

                questionBox.setText("");
                optionABox.setText("");
                optionBBox.setText("");
                optionCBox.setText("");
                optionDBox.setText("");
                answerBox.setText("");
                questionCounter[0] += 1;
                questionNumTxt.setText("Question " + Integer.toString(questionCounter[0]));

                Toast.makeText(CreateQuiz.this, "Question added", Toast.LENGTH_SHORT).show();
            }
        });

        createQuiz = findViewById(R.id.btn_createQuiz);
        createQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadQuizData();
                quizNameBox.setText("");
                Toast.makeText(CreateQuiz.this, "Quiz created!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), ScrollLecturerCourse.class));

            }
        });

    }

    public void addQuestionToDb(String key, DatabaseReference ref, String question, String optionA, String optionB, String optionC, String optionD, String answer, String questionNo)
    {
        String questionNum = "Q" + questionNo;
        ref.child(key).child("Questions").child(questionNum).child("Question").setValue(question);
        ref.child(key).child("Questions").child(questionNum).child("A").setValue(optionA);
        ref.child(key).child("Questions").child(questionNum).child("B").setValue(optionB);
        ref.child(key).child("Questions").child(questionNum).child("C").setValue(optionC);
        ref.child(key).child("Questions").child(questionNum).child("D").setValue(optionD);
        ref.child(key).child("Questions").child(questionNum).child("Answer").setValue(answer);

    }





    public void uploadQuizData()
    {


        String quizName = quizNameBox.getText().toString();
        DatabaseReference ref;
        ref = FirebaseDatabase.getInstance().getReference("Quizzes");
        String key = ref.push().getKey();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //ref.child(key).child("Course").setValue("Potions");

                for (int i = 0; i < questions.size(); ++i)
                {
                    ArrayList<String> currentQuestion = questions.get(i);
                    String question = currentQuestion.get(0);
                    String optionA = currentQuestion.get(1);
                    String optionB = currentQuestion.get(2);
                    String optionC = currentQuestion.get(3);
                    String optionD = currentQuestion.get(4);
                    String answer = currentQuestion.get(5);

                    addQuestionToDb(key, ref, question, optionA, optionB, optionC, optionD, answer, Integer.toString(i+1) );

                }

                String courseName = LecturerCoursesFragment.courseNameClicked;
                String topicName = ScrollLecturerCourse.topicNameClicked;

                ref.child(key).child("Course").setValue(courseName);
                ref.child(key).child("Topic").setValue(topicName);
                ref.child(key).child("name").setValue(quizName);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


}