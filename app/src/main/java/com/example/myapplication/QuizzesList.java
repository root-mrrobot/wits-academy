package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class QuizzesList extends AppCompatActivity {

    //TextView heading;
    ArrayList<String> StringNames = new ArrayList<String>();
    ListView quizzesList;
    private DatabaseReference quizzesRef;
    //public static  String topicsClicked,topicNameClicked;
    //public static String courseName, topic;
    public static String quizNameClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizzes_list);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        ArrayAdapter<String> myArray_topic_Adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, StringNames);


        String course = StudentSubscriptionsFragment.courseNameClicked;
        String topic = SubbedCourseTopics.topicNameClicked;

        quizzesList = findViewById(R.id.listView_quizzes);
//        heading = findViewById(R.id.txtHeading);
//        String name1 = StudentSubscriptionsFragment.courseNameClicked;
//        heading.setText(name1);
//        System.out.println(name1);
        quizzesRef= FirebaseDatabase.getInstance().getReference("Quizzes");
        quizzesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //ArrayList<String> topicIDs = new ArrayList<>();
                ArrayList<String> quizNames = new ArrayList<>();
                for(DataSnapshot child:snapshot.getChildren()){
                    //String id = child.getKey();
                    String name = child.child("name").getValue().toString();

                    String quizCourse = child.child("Course").getValue().toString();
                    String quizTopic = child.child("Topic").getValue().toString();


                    if (quizCourse.equals(course) && quizTopic.equals(topic))
                    {

                        StringNames.add(name);

                        //topicIDs.add(id);
                        quizNames.add(name);

                    }



                }
                myArray_topic_Adapter.notifyDataSetChanged();
                quizzesList.setAdapter(myArray_topic_Adapter);

                quizzesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        quizNameClicked = quizNames.get(i);

                        Intent intent = new Intent(getApplicationContext(), TakeQuiz.class);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}