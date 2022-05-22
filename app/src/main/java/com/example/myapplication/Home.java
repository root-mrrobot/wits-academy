package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Home extends AppCompatActivity implements View.OnClickListener{

    //creating variables
    TextView fullName;
    FirebaseUser fAuth;
    DatabaseReference fdata;
    String userId;
    private ImageButton student;
    private ImageButton lecturer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //constants
        fullName = findViewById(R.id.full_Name);
        fAuth = FirebaseAuth.getInstance().getCurrentUser() ;
        //assert fAuth != null;
        if (fAuth != null) {
            userId = fAuth.getUid();
        }
        fdata = FirebaseDatabase.getInstance().getReference();
        student =  findViewById(R.id.btnStudent);
        student.setOnClickListener(this);

        lecturer =  findViewById(R.id.btnLecturer);
        lecturer.setOnClickListener(this);

        //database object for referencing objects in database for use
        DatabaseReference data_ref = FirebaseDatabase.getInstance().getReference("Users/" + userId);
        data_ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String val = snapshot.child("fullName").getValue(String.class);
                fullName.setText(val);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    //when student button pressed takes user to student view
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnStudent:
                // Sending to Student View
                Intent intentStudent = new Intent(this, StudentNavigation.class);
                // Prevents user to go back to Home Page
                intentStudent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intentStudent);
                break;
            case R.id.btnLecturer:
                // Sending to Lecturer View
                Intent intentLecturer = new Intent(this, LecturerNavigation.class);
                // Prevents user to go back to Home Page
                intentLecturer.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intentLecturer);
                break;
        }
    }


}