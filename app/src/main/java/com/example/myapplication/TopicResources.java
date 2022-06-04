package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TopicResources extends AppCompatActivity{

    Button getPdf;
    Button getVideo;
    Intent intent;
    ArrayList<String> videoName = new ArrayList<String>();
    ArrayList<String> pdfName = new ArrayList<String>();
    ArrayList<String> videoURL = new ArrayList<String>();
    ArrayList<String> pdfURL = new ArrayList<String>();
    ListView videoList, pdfList;
    public static  String pdfClicked,videoClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_resources);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        intent = getIntent();

        Bundle extras = intent.getExtras();
//
//        String course = extras.getString("courseName");
//        String topic = extras.getString("topic");

        String course = StudentSubscriptionsFragment.courseNameClicked;
        String topic = SubbedCourseTopics.topicNameClicked;

        fetchUrls(course,topic);

    }

    public void retrievePdf(ArrayList pdf)
    {
        Intent intent = new Intent (Intent.ACTION_VIEW);
        intent.setType("application/pdf");


        pdfList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                pdfClicked = pdf.get(i).toString();
                System.out.println("pdfClicked " + pdfClicked);
                intent.setData(Uri.parse(pdfClicked));
                startActivity(intent);
            }
        });
    }


    public void retrieveVideo(ArrayList video)
    {
        Intent intent = new Intent (Intent.ACTION_VIEW);
        intent.setType("application/video");


        videoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                videoClicked = video.get(i).toString();
                System.out.println("videoClicked " + videoClicked);
                intent.setData(Uri.parse(videoClicked));
                startActivity(intent);
            }
        });
    }



    public void fetchUrls(String course, String topic)
    {

        ArrayAdapter<String> videoAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, videoName);
        //test = findViewById(R.id.textView2);
        videoList = findViewById(R.id.videoListView);

        ArrayAdapter<String> pdfAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, pdfName);
        //test = findViewById(R.id.textView2);
        pdfList = findViewById(R.id.pdfListView);

        DatabaseReference vidRef = FirebaseDatabase.getInstance().getReference("myvideos/");
        vidRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String videoUrl = null;
                for(DataSnapshot child : snapshot.getChildren())
                {
                    String courseName = child.child("subject_name").getValue().toString();
                    String topicName = child.child("topic_name").getValue().toString();

                    if (courseName.equals(course) && topicName.equals(topic))
                    {
                        videoUrl = child.child("vurl").getValue().toString();
                        videoName.add(child.child("title").getValue().toString());
                        videoURL.add(videoUrl);
                        System.out.println("vid matches");
                    }
                }


                // on data change 2
                DatabaseReference pdfRef = FirebaseDatabase.getInstance().getReference("pdfUploads/");
                String finalVideoUrl = videoUrl;

                pdfRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String pdfUrl = null;
                        for(DataSnapshot child : snapshot.getChildren())
                        {
                            String courseName = child.child("subject_name").getValue().toString();
                            String topicName = child.child("topic_name").getValue().toString();

                            if (courseName.equals(course) && topicName.equals(topic))
                            {
                                pdfUrl = child.child("url_pdf").getValue().toString();
                                System.out.println("pdf matches");
                                pdfName.add(child.child("name_pdf").getValue().toString());
                                pdfURL.add(pdfUrl);

                            }




                        }
                        retrieveVideo(videoURL);
                        retrievePdf(pdfURL);
                        pdfAdapter.notifyDataSetChanged();
                        pdfList.setAdapter(pdfAdapter);
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                videoAdapter.notifyDataSetChanged();
                videoList.setAdapter(videoAdapter);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}

