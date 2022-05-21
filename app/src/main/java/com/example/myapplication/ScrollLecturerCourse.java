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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ScrollLecturerCourse extends AppCompatActivity {
    TextView CourseName;
    Button addTopicBtn;
    EditText topic;
    int count = -1;

    private DatabaseReference topicsRef,ref;
    TopicData tData;
    TextView viewTopics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_lecturer_course);

        String name = LecturerCoursesFragment.courseNameClicked;
        CourseName = findViewById(R.id.SVcourseName);
        topic = findViewById(R.id.topic);

        topicsRef = FirebaseDatabase.getInstance().getReference("Topics/" + name + "/");
        CourseName.setText(name);

        addTopicBtn = findViewById(R.id.AddTopicBtn);

        viewTopics = findViewById(R.id.test);

        viewTopics.setOnClickListener(new View.OnClickListener() {
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
        String t_one = topic.getText().toString();

        ref = FirebaseDatabase.getInstance().getReference("courses/");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                // below line is for checking weather the
                // edittext fields are empty or not.
                if (TextUtils.isEmpty(t_one)) {
                    // if the text fields are empty
                    // then show the below message.
                    Toast.makeText(ScrollLecturerCourse.this, "Please add a topic", Toast.LENGTH_SHORT).show();
                }

                else {
                    topicsRef.child(ref.push().getKey()).setValue(t_one);
                    topic.setText("");
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