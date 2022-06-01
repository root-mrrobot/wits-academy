package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StudentCoursePopUp extends AppCompatActivity {
    String courseName;
    boolean isClicked = false;
    Button subscribe, exit;
    DatabaseReference userRef;
    FirebaseUser fAuth = FirebaseAuth.getInstance().getCurrentUser() ;
    String userId = fAuth.getUid();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_course_pop_up);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        // initialize all xml objects that appear on the pop up page
        ImageView image = (ImageView)findViewById(R.id.image);
        TextView course_name = (TextView)findViewById(R.id.course_name);
        TextView course_description = (TextView)findViewById(R.id.course_rating);
        TextView category = (TextView)findViewById(R.id.category);
        RatingBar ratingbar = (RatingBar)findViewById(R.id.ratingBar) ;
        TextView lecturer_name = (TextView)findViewById(R.id.lecturer_name);

        // initialize a getIntent
        Intent intent = getIntent();
        // initialize a bundle called extras to retrieve information from student home fragment
        Bundle extras = intent.getExtras();

        courseName = extras.getString("courseName");
        subscribe = findViewById(R.id.subscribe_button);

        // set all the objects to the matching information that was retrieved using the getExtras method
        course_name.setText(extras.getString("courseName"));
        course_description.setText("Description: " + extras.getString("courseDescription"));
        category.setText(extras.getString("category"));
        ratingbar.setRating(Float.parseFloat(extras.getString("rating")));
        lecturer_name.setText(extras.getString("lecturerName"));

        Glide.with(getApplicationContext()).load(extras.getString("image")).into(image);

        //boolean isSubbed = extras.getBoolean("isSubbed");


        userRef = FirebaseDatabase.getInstance().getReference("Users/" + userId + "/Subscriptions/");
        // This fetches the data from firebase
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                boolean isSubbed = false;
                for(DataSnapshot newChild : snapshot.getChildren())
                {
                    if (courseName.equals(newChild.getValue().toString()))
                    {
                        isSubbed = true;

                    }
                }

                if(isSubbed){
                    subscribe.setEnabled(false);
                }else{
                    subscribe.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            addCoursesToDB(courseName);
                            subscribe.setEnabled(false);
                        }
                    });
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });



        exit = findViewById(R.id.exit_button);

        exit.setOnClickListener(new View.OnClickListener() { // Button to return to the Student Courses Fragment
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), StudentNavigation.class));
            }
        });
    }

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Subscriptions");

    String key = ref.push().getKey();
    private void addCoursesToDB(String courseName) {
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ref.child(key).setValue(courseName);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
