package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ScrollLecturerCourse extends AppCompatActivity {
    TextView CourseName;
    Button addTopicBtn;
    EditText topic;
    int count = -1;
    ArrayList<String> StringNames = new ArrayList<String>();
    ListView myListTopics;
    private DatabaseReference coursesRef;
    public static  String topicsClicked,topicNameClicked;

    private DatabaseReference topicsRef,ref;
    TopicData tData;
    TextView viewTopics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_lecturer_course);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        String name = LecturerCoursesFragment.courseNameClicked;
        CourseName = findViewById(R.id.SVcourseName);
        topic = findViewById(R.id.topic);

        topicsRef = FirebaseDatabase.getInstance().getReference("Topics/" + name + "/");
        CourseName.setText(name);

        addTopicBtn = findViewById(R.id.AddTopicBtn);

        addTopicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UploadtoFirebase();
                StringNames.clear();
            }
        });

        ArrayAdapter<String> myArray_topic_Adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, StringNames);
        //test = findViewById(R.id.textView2);
        myListTopics = findViewById(R.id.topicView);
        String name1 = LecturerCoursesFragment.courseNameClicked;
        coursesRef= FirebaseDatabase.getInstance().getReference("Topics/" + name1);
        coursesRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                String nameVal = snapshot.child("Week 1").getValue(String.class);
//               System.out.println(nameVal);
//                test.setText(nameVal);


                ArrayList<String> topicIDs = new ArrayList<>();
                ArrayList<String> topicNames = new ArrayList<>();
                for(DataSnapshot child:snapshot.getChildren()){
                    String id = child.getKey();
                    String name = child.getValue().toString();
                    StringNames.add(name);

                    topicIDs.add(id);
                    topicNames.add(name);

                }
                myArray_topic_Adapter.notifyDataSetChanged();
                myListTopics.setAdapter(myArray_topic_Adapter);

                myListTopics.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        topicsClicked = topicIDs.get(i);
                        topicNameClicked = topicNames.get(i);

                        startActivity(new Intent(getApplicationContext(), LecturerResourceView.class));
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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