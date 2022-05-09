package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
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

public class info_courses extends AppCompatActivity {

    FirebaseUser fAuth;
    String courseId;
    public static TextView Cname;
    DatabaseReference ref;
    TextView returnCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_courses);
        String course_id = LecturerCoursesFragment.courseClicked;
        String name = LecturerCoursesFragment.courseNameClicked;
        fAuth = FirebaseAuth.getInstance().getCurrentUser();


        returnCourse = findViewById(R.id.returnCourseButton);

        returnCourse.setOnClickListener(new View.OnClickListener() { // Button to return to the Lecturer COurses Fragment
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LecturerNavigation.class));
            }
        });


        if (fAuth != null) { //Firebase Authorization
            courseId = fAuth.getUid();
        }


        Cname = findViewById(R.id.crs_name);
        Cname.setText(name);
        ImageView Cimg = findViewById(R.id.crs_image);
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

                    //get the child of the course that we clicked on
                    String coursesTableName = child.child("name").getValue().toString();


                    // loading that data into rImage
                    //Displays the courses information into the TextViews and retrieves the image associated with it
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
                       Glide.with(getApplicationContext()).load(link).into(Cimg);

                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}
