
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class LecturerCoursesFragment extends Fragment implements View.OnClickListener {
    ListView myListCourses;
    ArrayList<Course> myArrayList = new ArrayList<Course>();
    ArrayList<String> c = new ArrayList<String>();
    DatabaseReference coursesRef;
    View view;
    Spinner spinner;
    FirebaseUser fAuth;
    String userId;
    public static  String courseClicked,courseNameClicked;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_student_home, container, false);
        ArrayAdapter<String> myArrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, c);

        fAuth = FirebaseAuth.getInstance().getCurrentUser() ;
        if(!LecturerCourseInfoChecks.noUser(fAuth)){
            userId = fAuth.getUid();
        }

        coursesRef= FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Lecturer created courses");

        spinner = view.findViewById(R.id.spinner2);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.Categories_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
//making a listview to display lecturer created courses
        coursesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                ArrayList<String> courseIDs = new ArrayList<>();
                ArrayList<String> courseNames = new ArrayList<>();

                for (DataSnapshot child : snapshot.getChildren()) {

                    //System.out.println(child);
                    String id = child.getKey();
                    String name = child.getValue().toString();
                    c.add(name);

                    courseIDs.add(id);
                    courseNames.add(name);



              }

                myArrayAdapter.notifyDataSetChanged();
                myListCourses.setAdapter(myArrayAdapter);


                DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

                myListCourses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        courseClicked = courseIDs.get(i);
                        courseNameClicked = courseNames.get(i);



                        startActivity(new Intent(getContext(), info_courses.class));
                    }
                });

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("The read failed: " + error.getMessage());
            }
        });

        myListCourses = (ListView) view.findViewById(R.id.listView);

        return view;
    }

    @Override
    public void onClick(View view) {

    }

}