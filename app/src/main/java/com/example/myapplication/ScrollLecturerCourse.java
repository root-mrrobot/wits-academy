package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ScrollLecturerCourse extends AppCompatActivity {
    TextView CourseName;
    Button addTopicBtn;
    EditText topic_one;
    EditText topic_two;
    EditText topic_three;
    EditText topic_four;
    EditText topic_five;
    private DatabaseReference topicsRef,ref;
    TopicData tData;
    TextView test;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_lecturer_course);


        String name = LecturerCoursesFragment.courseNameClicked;
        CourseName = findViewById(R.id.SVcourseName);
        topic_one = findViewById(R.id.topic_one);
        topic_two = findViewById(R.id.topic_2);
        topic_three = findViewById(R.id.topic_3);
        topic_four = findViewById(R.id.topic_4);






        topicsRef = FirebaseDatabase.getInstance().getReference("Topics/" + name + "/");
        CourseName.setText(name);

        addTopicBtn = findViewById(R.id.AddTopicBtn);

        test = findViewById(R.id.test);

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ScrollLecturerCourse.this, DisplayTopics.class));
            }
        });

        addTopicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UploadtoFirebase();

            }
        });

    }

    private void UploadtoFirebase(){
        String t_one = topic_one.getText().toString();
        String t_two = topic_two.getText().toString();
        String t_three = topic_three.getText().toString();
        String t_four = topic_four.getText().toString();

        System.out.println("reached 4");

        ref = FirebaseDatabase.getInstance().getReference("courses/");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

//                String key = topicsRef.push()
//                String key1 = topicsRef.push().getKey();
//                String key2 = topicsRef.push().getKey();
//                String key3= topicsRef.push().getKey();

                System.out.println("reached1");

                // below line is for checking weather the
                // edittext fields are empty or not.
                if (TextUtils.isEmpty(t_one) && TextUtils.isEmpty(t_two) && TextUtils.isEmpty(t_three) && TextUtils.isEmpty(t_four)) {
                    // if the text fields are empty
                    // then show the below message.
                    Toast.makeText(ScrollLecturerCourse.this, "Please add some data.", Toast.LENGTH_SHORT).show();
                    System.out.println("reached2");
                }

                else {
                    topicsRef.child("Week 1").setValue(t_one);
                    topicsRef.child("Week 2").setValue(t_two);
                    topicsRef.child("Week 3").setValue(t_three);
                    topicsRef.child("Week 4").setValue(t_four);
                    topic_one.setText("");
                    topic_two.setText("");
                    topic_three.setText("");
                    topic_four.setText("");
                    System.out.println("reached3");



                    // after adding this data we are showing toast message.
                    Toast.makeText(ScrollLecturerCourse.this, "data added", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ScrollLecturerCourse.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();
            }


        });
    }

    //onclicklisteners goes through this main function for listeners to determine where to go.



}