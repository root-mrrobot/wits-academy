package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_student_home, container, false);
        ArrayAdapter<String> myArrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,c);

        coursesRef= FirebaseDatabase.getInstance().getReference("courses/");
        coursesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot child : snapshot.getChildren()) {
                    String id = child.getKey();
                    String name = child.child("name").getValue().toString();

                    Course course = new Course();

                    course.setName(name);
                    course.setId(id);
                    myArrayList.add(course);

                    for (int i = 0; i < myArrayList.size(); i++){
                        Course courses = myArrayList.get(i);

                        String courseName = course.getName();
                        String courseId = course.getId();
                        String category = course.category;
                        String description = course.description;

                        String singleCourse = courseName + "\n" + category + "\n" + description;

                        c.add(singleCourse);

                        myArrayAdapter.notifyDataSetChanged();
                        myListCourses.setAdapter(myArrayAdapter);
                    }


                }

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