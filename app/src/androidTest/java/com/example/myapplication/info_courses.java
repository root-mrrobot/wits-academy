package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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
    Button course_content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        String course_id = LecturerCoursesFragment.courseClicked;
        String name;

        if (LecturerCoursesFragment.isLecturerView){

            setContentView(R.layout.activity_info_courses);
            name = LecturerCoursesFragment.courseNameClicked;

            returnCourse = findViewById(R.id.returnCourseButton);

            returnCourse.setOnClickListener(new View.OnClickListener() { // Button to return to the Lecturer COurses Fragment
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getApplicationContext(), LecturerNavigation.class));
                }
            });

            course_content = findViewById(R.id.course_contentBtn);
            course_content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getApplicationContext(), ScrollLecturerCourse.class));
                }
            });
        }
        else{
            name = StudentSubscriptionsFragment.courseNameClicked;
            setContentView(R.layout.subscribed_course);

            Button courseResources = findViewById(R.id.btnViewCourse);

            courseResources.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getApplicationContext(), SubbedCourseTopics.class));
                    System.out.println("button works");
                }
            });

            // Referencing unsub button from xml file
        /*    Button unsub = findViewById(R.id.btnUnsubscribe);

            // setting on Click Listener
            unsub.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Alert Dialog init
                    AlertDialog.Builder unsub_dialog = new AlertDialog.Builder(view.getContext());
                    // Title of Alert Dialog
                    unsub_dialog.setTitle("Unsubscribe from " + name);
                    // Message of Dialog
                    unsub_dialog.setMessage("Are you sure you would like to Unsubscribe?");

                    // creating yes and no buttons for pop up
                    unsub_dialog.setPositiveButton("Unsubscribe", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // Sending user to Login Page - User has logged out
//                        Intent intent = new Intent(getActivity(), Login.class);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                        startActivity(intent);
//                        Toast.makeText(getActivity(),"You have successfully logged out. Please come back again!",Toast.LENGTH_SHORT).show();
                        }
                    });

                    unsub_dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // Close pop up and go back to subscriptions page
                        }
                    });

                    unsub_dialog.create().show(); // Show the pop up
                }
            });
*/
            // Referencing rating button from xml file
            Button rate = findViewById(R.id.btnRate);

            // setting on Click Listener
            rate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Alert Dialog init
                    AlertDialog.Builder rating_dialog = new AlertDialog.Builder(view.getContext());
                    // Title of Alert Dialog
                    rating_dialog.setTitle("Rate Selected Course");
                    // Message of Dialog
                    rating_dialog.setMessage("Select a star Rating that you are happy with:");

                    // creating yes and no buttons for pop up
                    rating_dialog.setPositiveButton("Rate", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // Sending user to Login Page - User has logged out
//                        Intent intent = new Intent(getActivity(), Login.class);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                        startActivity(intent);
//                        Toast.makeText(getActivity(),"You have successfully logged out. Please come back again!",Toast.LENGTH_SHORT).show();
                        }
                    });

                    rating_dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // Close pop up and go back to subscriptions page
                        }
                    });

                    rating_dialog.create().show(); // Show the pop up
                }
            });
        }


        fAuth = FirebaseAuth.getInstance().getCurrentUser();



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

                        System.out.println("name " + name);
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

//    public void onClick(View view){
//        switch (view.getId()){
//            case R.id.btnUnsubscribe:
//                // Alert Dialog init
//                AlertDialog.Builder unsub_dialog = new AlertDialog.Builder(view.getContext());
//                // Title of Alert Dialog
//                unsub_dialog.setTitle("Log out of Wits Academy");
//                // Message of Dialog
//                unsub_dialog.setMessage("Are you sure you would like to log out?");
//
//                // creating yes and no buttons for pop up
//                unsub_dialog.setPositiveButton("Unsubscribe", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                    }
//                });
//        }
//    }

}
