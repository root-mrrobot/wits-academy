package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TopicResources extends AppCompatActivity{

    Button getPdf;
    Button getVideo;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_resources);

        intent = getIntent();

        Bundle extras = intent.getExtras();

        String course = extras.getString("courseName");
        String topic = extras.getString("topic");


        fetchUrls(course,topic);

    }

    public void retrievePdf(String url)
    {

        getPdf = findViewById(R.id.btn_getPdf);

        if (url != null)
        {
            Intent intent = new Intent (Intent.ACTION_VIEW);
            intent.setType("application/pdf");
            intent.setData(Uri.parse(url));

            getPdf.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(intent);

                }
            });
        }

        else
        {
            getPdf.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(TopicResources.this,"No PDF document is currently available for this document",Toast.LENGTH_LONG).show();

                }
            });
        }




    }


    public void retrieveVideo(String url)
    {

        getVideo = findViewById(R.id.btn_getVideo);
        if (url != null)
        {

            Intent intent = new Intent (Intent.ACTION_VIEW);
            intent.setType("application/video");
            intent.setData(Uri.parse(url));

            getVideo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(intent);

                }
            });
        }

        else
            getVideo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(TopicResources.this,"No video is currently available for this topic",Toast.LENGTH_LONG).show();

                }
            });



    }



    public void fetchUrls(String course, String topic)
    {

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

                            }




                        }


                        retrieveVideo(finalVideoUrl);
                        retrievePdf(pdfUrl);


                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                // ends

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}