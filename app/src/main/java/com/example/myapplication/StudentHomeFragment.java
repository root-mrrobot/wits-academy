package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.R;
import com.google.android.gms.common.util.JsonUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Random;
import java.util.jar.JarOutputStream;


public class StudentHomeFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    ListView myListCourses;
    ArrayList<Course> myArrayList = new ArrayList<Course>();
    ArrayList<String> c = new ArrayList<String>();
    ArrayList<String> fullC = new ArrayList<String>();
    DatabaseReference coursesRef;
    View view;
    Spinner spinner;
    String[] catArray;
    float[] r = new float[] { 0.5f, 1, 1.5f, 2, 2.5f, 3, 3.5f, 4, 4.5f, 5};



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        catArray = getContext().getResources().getStringArray(R.array.Categories_array);

        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_student_home, container, false);


        spinner = view.findViewById(R.id.spinner2);
        spinner.setOnItemSelectedListener(this);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),R.array.Categories_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        this.display();

        myListCourses=(ListView) view.findViewById(R.id.listView);

        return view;
    }

    public void display(){
        ArrayAdapter<String> myArrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,c);

        coursesRef= FirebaseDatabase.getInstance().getReference("courses/");
        coursesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot child : snapshot.getChildren()) {

                    String id = child.getKey();
                    String name = child.child("name").getValue().toString();
                    String description = child.child("description").getValue().toString();
                    String category = child.child("courseCategory").getValue().toString();
                    float rating = r[new Random().nextInt(r.length)];

                    if (category.equals(spinner.getSelectedItem().toString())) {
                        Course course = new Course();
                        course.setName(name);
                        course.setDescription((description));
                        course.setCategory(category);
                        course.setId(id);
                        course.setRating(rating);
                        myArrayList.add(course);

                        String singleCourse = "\nCourse Name: " + name + "\nTeacher:" + "\nRating: " + rating;
                        c.add(singleCourse);
                    }
                    else if (spinner.getSelectedItem().toString().equals("All Courses")){
                        Course course = new Course();
                        course.setName(name);
                        course.setDescription((description));
                        course.setCategory(category);
                        course.setId(id);
                        myArrayList.add(course);

                        String fullCourse = id + "\n" + name + "\n" + category + "\n" + description + "\n" + rating;
                        fullC.add(fullCourse);

                        String singleCourse =  "\nCourse Name: " + name + "\nTeacher:" + "\nRating: " + rating;
                        c.add(singleCourse);

                    }
                }
                myArrayAdapter.notifyDataSetChanged();
                myListCourses.setAdapter(myArrayAdapter);

                // open the popup activity and display the course information according to what was clicked on
                myListCourses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                        String course = fullC.get(position);
                        String[] splitCourse = course.split(":|\\\n");
                        System.out.println(course);


                        String courseName = splitCourse[1];
                        //String courseId = splitCourse[];
                        String category = splitCourse[2];
                        String description = splitCourse[3];
                        String rating = splitCourse[4];

                        Bundle extras = new Bundle();


                        extras.putString("courseName", courseName);
                        extras.putString("courseDescription", description);
                        extras.putString("category", category);
                        extras.putString("rating", rating);
                        //intent.putExtra("lecturerName", lecturer);

                        Intent intent = new Intent(view.getContext(), StudentCoursePopUp.class);
                        intent.putExtras(extras);
                        startActivity(intent);
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("The read failed: " + error.getMessage());
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        // make toast of name of course
        // which is selected in spinner
        Toast.makeText(getActivity().getApplicationContext(),
                catArray[i],
                Toast.LENGTH_LONG)
                .show();

        myListCourses.setAdapter(null);
        c.clear();
        this.display();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


}