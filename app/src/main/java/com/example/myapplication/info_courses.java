package com.example.myapplication;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class info_courses extends AppCompatActivity {

    FirebaseUser fAuth;
    String courseId;
    public static TextView Cname;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_courses);
        String course_id = LecturerCoursesFragment.courseClicked;
        String name = LecturerCoursesFragment.courseNameClicked;
        fAuth = FirebaseAuth.getInstance().getCurrentUser();

        if (fAuth != null) {
            courseId = fAuth.getUid();
        }


        Cname = findViewById(R.id.test);
        Cname.setText(name);
        ImageView Cimg = findViewById(R.id.imageView2);
        TextView Cdesc = findViewById(R.id.crs_desc);
        TextView Ccat = findViewById(R.id.crs_category);

        // Referencing Firebase Database to get Users

        ref = FirebaseDatabase.getInstance().getReference("courses/");


        String key = ref.getKey();
        // This fetches the data from firebase
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {


                    String coursesTableName = child.child("name").getValue().toString();


                    // loading that data into rImage

                    if (coursesTableName.equals(name))
                    {
                        System.out.println("is equal");
                        String description = child.child("description").getValue().toString();//getting description of course ot display
                        String Category = child.child("courseCategory").getValue().toString();
                        System.out.println(Category);
                        String link = child.child("imageUri").getValue().toString();
                        Ccat.setText(Category);
                        Cdesc.setText(description);
                        // variable which is ImageView
                       Picasso.get().load(link).into(Cimg);




                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}
