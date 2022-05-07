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

import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class StudentHomeFragment extends Fragment implements View.OnClickListener {
    ListView myListCourses;
    ArrayList<Course> myArrayList = new ArrayList<Course>();
    ArrayList<String> c = new ArrayList<String>();
    DatabaseReference coursesRef;
    View view;
    Spinner spinner;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_student_home, container, false);
        ArrayAdapter<String> myArrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,c);

        coursesRef= FirebaseDatabase.getInstance().getReference("courses/");

        spinner = view.findViewById(R.id.spinner2);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),R.array.Categories_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        coursesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot child : snapshot.getChildren()) {

                    String id = child.getKey();
                    String name = child.child("name").getValue().toString();
                    String description = child.child("description").getValue().toString();
                    String category = child.child("courseCategory").getValue().toString();
                    if (category.equals(spinner.getSelectedItem().toString())) {
                        myListCourses.setAdapter(null);
                        Course course = new Course();
                        course.setName(name);
                        course.setDescription(description);
                        course.setCategory(category);
                        course.setId(id);
                        myArrayList.add(course);

                        String singleCourse = "Category: " + category + "\nCourse Name: " + name + "\nDescription: " + description + "\n";
                        c.add(singleCourse);



                    }
                    else if (spinner.getSelectedItem().toString().equals("All Courses")){
                        myListCourses.setAdapter(null);
                        Course course = new Course();
                        course.setName(name);
                        course.setDescription((description));
                        course.setCategory(category);
                        course.setId(id);
                        myArrayList.add(course);

                        String singleCourse = "Category: " + category + "\nCourse Name: " + name + "\nDescription: " + description + "\n";
                        c.add(singleCourse);
                    }else{
                        myListCourses.setAdapter(null);
                        String singleCourse = "No Courses Available";
                        c.add(singleCourse);
                    }

                }
                myArrayAdapter.notifyDataSetChanged();
                myListCourses.setAdapter(myArrayAdapter);

                // open the popup activity and display the course information according to what was clicked on
                myListCourses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                        String course = c.get(position);
                        String[] splitCourse = course.split(":|\\\n");


                        String courseName = splitCourse[3];
                        //String courseId = splitCourse[];
                        String category = splitCourse[1];
                        String description = splitCourse[5];

                        Bundle extras = new Bundle();


                        extras.putString("courseName", courseName);
                        extras.putString("courseDescription", description);
                        extras.putString("category", category);
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

        myListCourses=(ListView) view.findViewById(R.id.listView);




        return view;


    }



    @Override
    public void onClick(View view) {

    }

}