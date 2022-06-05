package com.example.myapplication;

import android.app.ActionBar;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

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
    String courseId, uID;
    public static TextView Cname;
    DatabaseReference ref;
    TextView returnCourse;
    Button course_content;
    float rating;
    private DatabaseReference coursesRef;
    private DatabaseReference rateRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        fAuth = FirebaseAuth.getInstance().getCurrentUser();

        if (fAuth != null) { //Firebase Authorization
            uID = fAuth.getUid();
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
            rateRef = FirebaseDatabase.getInstance().getReference("Ratings/" + name + "/");
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
                    // Rating bar init
                    RatingBar ratingBar = new RatingBar(view.getContext());
                    //sets number of stars and intervals of rating
                    ratingBar.setNumStars(5);
                    ratingBar.setStepSize(0.5f);
                    // Title of Alert Dialog
                    rating_dialog.setTitle("Rate " + name);
                    // Message of Dialog
                    rating_dialog.setMessage("Select a star Rating that you are happy with:");

                    ratingBar.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT,
                            ActionBar.LayoutParams.WRAP_CONTENT));
                    LinearLayout parent = new LinearLayout(view.getContext());
                    parent.setGravity(Gravity.CENTER);
                    parent.setLayoutParams(new ActionBar.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.MATCH_PARENT));
                    parent.addView(ratingBar);

                    rating_dialog.setView(parent);


                    // creating yes and no buttons for pop up
                    rating_dialog.setPositiveButton("Rate", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            rating = ratingBar.getRating();

                            Rate rateData = new Rate(uID, name, rating);

                            ref = FirebaseDatabase.getInstance().getReference("courses/");
                            ref.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {

                                        rateRef.child(ref.push().getKey()).setValue(rateData);
                                        ratingBar.setRating(0);
                                        // after adding this data we are showing toast message.
                                        Toast.makeText(info_courses.this, "data added", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    Toast.makeText(info_courses.this, "Fail to add rating " + error, Toast.LENGTH_SHORT).show();
                                }
                            });
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




}
