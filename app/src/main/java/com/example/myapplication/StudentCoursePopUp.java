package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class StudentCoursePopUp extends AppCompatActivity implements View.OnClickListener {
    String courseName;
    boolean isClicked = false;
    Button subscribe;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_course_pop_up);

        // initialize all xml objects that appear on the pop up page
        ImageView image = (ImageView)findViewById(R.id.image);
        TextView course_name = (TextView)findViewById(R.id.course_name);
        TextView course_description = (TextView)findViewById(R.id.course_description);
        TextView category = (TextView)findViewById(R.id.category);
        RatingBar ratingbar = (RatingBar)findViewById(R.id.ratingBar) ;
        TextView lecturer_name = (TextView)findViewById(R.id.lecturer_name);

        // initialize a getIntent
        Intent intent = getIntent();
        // initialize a bundle called extras to retrieve information from student home fragment
        Bundle extras = intent.getExtras();

        courseName = extras.getString("courseName");


//          System.out.println("popup " + isSubbed);

        subscribe = findViewById(R.id.subscribe_button);

        // set all the objects to the matching information that was retrieved using the getExtras method
        course_name.setText(extras.getString("courseName"));
        course_description.setText("Description: " + extras.getString("courseDescription"));
        category.setText(extras.getString("category"));
        ratingbar.setRating(Float.parseFloat(extras.getString("rating")));
        lecturer_name.setText(extras.getString("lecturerName"));

        Glide.with(getApplicationContext()).load(extras.getString("image")).into(image);

        // TODO: onClickListener for the exit button and the subscribe button(Sprint 3)


//        subscribe.setOnClickListener((View.OnClickListener) this);

        boolean isSubbed = extras.getBoolean("isSubbed");

        if(isSubbed){
            subscribe.setEnabled(false);
        }else{
            subscribe.setOnClickListener((View.OnClickListener) this);
        }

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.subscribe_button:

                addCoursesToDB(courseName);

            /*    Intent intent = new Intent(view.getContext(), StudentCoursePopUp.class);
                intent.putExtras(intent.getExtras());
                finish();
                startActivity(intent);*/
                subscribe.setEnabled(false);


            case R.id.exit_button:

                // TODO: exit
        }
    }

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Subscriptions");


    String key = ref.push().getKey();

    private void addCoursesToDB(String courseName) {

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //ref.child(key).removeValue();
                ref.child(key).setValue(courseName);



                    }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                 }
        });
    }


    }
