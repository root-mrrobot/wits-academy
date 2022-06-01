package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SubbedCourseTopics extends AppCompatActivity {
    TextView heading;
    ArrayList<String> StringNames = new ArrayList<String>();
    ListView myListTopics;
    private DatabaseReference coursesRef;
    public static  String topicsClicked,topicNameClicked;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_topics);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        ArrayAdapter<String> myArray_topic_Adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, StringNames);

        myListTopics = findViewById(R.id.topicView);
        heading = findViewById(R.id.txtHeading);
        String name1 = StudentSubscriptionsFragment.courseNameClicked;
        heading.setText(name1);
        System.out.println(name1);
        coursesRef= FirebaseDatabase.getInstance().getReference("Topics/" + name1);
        coursesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

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

                        Bundle extras = new Bundle();
                        extras.putString("courseName", name1);
                        extras.putString("topic", topicNameClicked);

                        Intent intent = new Intent(getApplicationContext(), TopicResources.class);
                        intent.putExtras(extras);
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
