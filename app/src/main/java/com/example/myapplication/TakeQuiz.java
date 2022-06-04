package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class TakeQuiz extends AppCompatActivity {

    TextView quizName;
    TextView questionText;
    TextView questionNumText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_quiz);

        quizName = findViewById(R.id.txt_quizName);
        quizName.setText(QuizzesList.quizNameClicked);

        retrieveQuizData();

    }

    public void retrieveQuizData()
    {


        String course = StudentSubscriptionsFragment.courseNameClicked;
        String topic = SubbedCourseTopics.topicNameClicked;
        String quizNameClicked = QuizzesList.quizNameClicked;

        DatabaseReference ref  = FirebaseDatabase.getInstance().getReference("Quizzes");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                ArrayList<ArrayList<String>> questions = new ArrayList<ArrayList<String>>();
                ArrayList<String> blankQuestion = new ArrayList<>();
                Collections.addAll(blankQuestion, null, null, null, null, null, null, null);
                questions.add(blankQuestion);

                for(DataSnapshot child:snapshot.getChildren()){

                    String name = child.child("name").getValue().toString();

                    String quizCourse = child.child("Course").getValue().toString();
                    String quizTopic = child.child("Topic").getValue().toString();
                    String quizId = child.getKey();


                    if (quizCourse.equals(course) && quizTopic.equals(topic) && name.equals(quizNameClicked))
                    {


                        for(DataSnapshot questionChild: snapshot.child(quizId).child("Questions").getChildren()) {
                            ArrayList<String> currentQuestion = new ArrayList<>();

                            String questionNum = questionChild.getKey();
                            String question = questionChild.child("Question").getValue().toString();
                            String optionA = questionChild.child("A").getValue().toString();
                            String optionB = questionChild.child("B").getValue().toString();
                            String optionC = questionChild.child("C").getValue().toString();
                            String optionD = questionChild.child("D").getValue().toString();
                            String answer = questionChild.child("Answer").getValue().toString();

                            Collections.addAll(currentQuestion, questionNum, question, optionA, optionB, optionC, optionD, answer);

                            questions.add(currentQuestion);

                        }


                    }



                }

                // quiz mechanics here



                questionText = findViewById(R.id.txt_question);
                questionNumText = findViewById(R.id.txt_questionNumber);

                RadioButton A = findViewById(R.id.A);
                RadioButton B = findViewById(R.id.B);
                RadioButton C = findViewById(R.id.C);
                RadioButton D = findViewById(R.id.D);

                RadioGroup radioGroup;


                radioGroup = (RadioGroup)findViewById(R.id.groupradio);

                radioGroup.clearCheck();

                radioGroup.setOnCheckedChangeListener(
                        new RadioGroup
                                .OnCheckedChangeListener() {
                            @Override

                            public void onCheckedChanged(RadioGroup group,
                                                         int checkedId)
                            {

                                RadioButton radioButton = (RadioButton)group.findViewById(checkedId);

                                System.out.println(checkedId);

                            }
                        });


                final int[] questionIndex = {0};

                final int[] correctAnswers = {0};

                Button next = findViewById(R.id.btn_next);
//
//                ArrayList<String> firstQuestion = questions.get(0);
//
//                String questionNum = firstQuestion.get(0);
//                String question = firstQuestion.get(1);
//                String optionA = firstQuestion.get(2);
//                String optionB = firstQuestion.get(3);
//                String optionC = firstQuestion.get(4);
//                String optionD = firstQuestion.get(5);
//                String answer = firstQuestion.get(6);
//
//                A.setText(optionA);
//                B.setText(optionB);
//                C.setText(optionC);
//                D.setText(optionD);
//
//                questionNumText.setText(questionNum);
//                questionText.setText(question);

//                final String[] selectedAnswer = new String[1];
//
//                int selectedId = radioGroup.getCheckedRadioButtonId();
//                if (selectedId == -1) {
//                    Toast.makeText(TakeQuiz.this,
//                            "No answer has been selected",
//                            Toast.LENGTH_SHORT)
//                            .show();
//                }
//                else {
//
//                    RadioButton radioButton
//                            = (RadioButton) radioGroup
//                            .findViewById(selectedId);
//
//                    radioButton.getText();
//
//                    for (int y = 0; y < firstQuestion.size(); ++y) {
//                        if (firstQuestion.get(y).equals(radioButton.getText())) {
//                            if (y == 2) {
//                                selectedAnswer[0] = "A";
//                            } else if (y == 3) {
//                                selectedAnswer[0] = "B";
//                            } else if (y == 4) {
//                                selectedAnswer[0] = "C";
//                            } else if (y == 5) {
//                                selectedAnswer[0] = "D";
//                            }
//                        }
//                    }
//
//                }
//
//                System.out.println("first answer " + selectedAnswer);


                    next.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            if (questionIndex[0] < questions.size())
                            {

                                System.out.println(questions.size());

                                ArrayList<String> currentQuestion = questions.get(questionIndex[0]);

                                final String[] selectedAnswer = new String[1];

                                int selectedId = radioGroup.getCheckedRadioButtonId();
                                if (selectedId == -1) {
                                    Toast.makeText(TakeQuiz.this,
                                            "No answer has been selected",
                                            Toast.LENGTH_SHORT)
                                            .show();
                                }
                                else {

                                    RadioButton radioButton
                                            = (RadioButton)radioGroup
                                            .findViewById(selectedId);

                                    radioButton.getText();
                                    System.out.println(radioButton.getText());

                                    for (int y = 0; y < currentQuestion.size(); ++y)
                                    {
                                        if(questionIndex[0] != 0)
                                        {
                                            if (currentQuestion.get(y).equals(radioButton.getText()))
                                            {
                                                if (y == 2)
                                                {
                                                    selectedAnswer[0] = "A";
                                                }

                                                else if (y == 3)
                                                {
                                                    selectedAnswer[0] = "B";
                                                }

                                                else if (y == 4)
                                                {
                                                    selectedAnswer[0] = "C";
                                                }

                                                else if (y ==5)
                                                {
                                                    selectedAnswer[0] = "D";
                                                }
                                            }
                                        }

                                    }


                                    if (questionIndex[0] != 0)
                                    {
                                        if (selectedAnswer[0].equals(currentQuestion.get(6)))
                                        {
                                            correctAnswers[0] += 1;
                                        }

                                    }


//                                    Toast.makeText(TakeQuiz.this,
//                                            selectedAnswer[0],
//                                            Toast.LENGTH_SHORT)
//                                            .show();
                                }


                                // if second last question

                                if (questionIndex[0] < questions.size() - 1)
                                {
                                    System.out.println("current q index " + questionIndex[0]);
                                    // display next question


                                    ArrayList<String> nextQuestion = questions.get(questionIndex[0] + 1);

                                    String questionNum = nextQuestion.get(0);
                                    String question = nextQuestion.get(1);
                                    String optionA = nextQuestion.get(2);
                                    String optionB = nextQuestion.get(3);
                                    String optionC = nextQuestion.get(4);
                                    String optionD = nextQuestion.get(5);
                                    String answer = nextQuestion.get(6);

                                    A.setText(optionA);
                                    B.setText(optionB);
                                    C.setText(optionC);
                                    D.setText(optionD);


                                    System.out.println("num" + questionNum);

                                    questionNumText.setText(questionNum);
                                    questionText.setText(question);

                                    next.setText("Next Question");
                                }

                                if (questionIndex[0] == questions.size() - 2)
                                {
                                    next.setText("Finish Quiz");
                                }

                                if (questionIndex[0] == questions.size() - 1)
                                {
                                    double corrects = correctAnswers[0];
                                    double total = questions.size() - 1;
                                    double score = Math.round((corrects/total) * 100);

                                    //questionText.setText(Double.toString(score));
                                    Intent intent = new Intent(TakeQuiz.this, displayQuizResults.class);
                                    String scoreExtra = Double.toString(score);
                                    intent.putExtra("scoreString", scoreExtra);
                                    startActivity(intent);
                                }


                                questionIndex[0] += 1;
                                radioGroup.clearCheck();


                            }



                            }




                    });









            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}