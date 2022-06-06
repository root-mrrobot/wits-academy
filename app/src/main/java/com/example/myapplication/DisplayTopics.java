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

public class DisplayTopics extends AppCompatActivity {
TextView test;
    ArrayList<String> StringNames = new ArrayList<String>();
    ListView myListTopics;
    private DatabaseReference coursesRef;
    public static  String topicsClicked,topicNameClicked;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_topics);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        ArrayAdapter<String> myArray_topic_Adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, StringNames);
        //test = findViewById(R.id.textView2);
        myListTopics = findViewById(R.id.topicView);
        String name1 = LecturerCoursesFragment.courseNameClicked;
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
                        startActivity(new Intent(getApplicationContext(), UploadLectures.class));
                    }
                });




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }
}
