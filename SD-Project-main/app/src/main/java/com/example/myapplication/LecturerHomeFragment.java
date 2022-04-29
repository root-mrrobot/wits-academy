package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class LecturerHomeFragment extends Fragment implements View.OnClickListener {
    private Button btnAddToDatabase;
    private EditText course_name;
    private static final String TAG = "AddToDatabase";
    private String userID;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;
    private DatabaseReference userRef;
    private String categories;
    private Spinner spinnerV;
    private DatabaseReference spinnerRef;
    private ArrayList<String> spinnerList;
    private ArrayAdapter<String> adapter;
    private TextView my_courses;
    View view;

    //buttonOk = dialogView.findViewById<Button>(R.id.buttonOk)

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_lecturer_home, container, false);

        //storeData(view);

        course_name = (EditText) view.findViewById(R.id.course_name);
        btnAddToDatabase = (Button) view.findViewById(R.id.add_course_name);
        spinnerV = view.findViewById(R.id.spinner);
        my_courses = view.findViewById(R.id.my_courses);
        // my_courses.setOnClickListener(this);

        userRef = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Lecturer created courses");
        myRef = FirebaseDatabase.getInstance().getReference()
                .child("courses");
        //spinnerRef=FirebaseDatabase.getInstance().getReference("Users");

        spinnerList = new ArrayList<>();
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, spinnerList);
        btnAddToDatabase.setOnClickListener(this);
        spinnerV.setAdapter(adapter);
        Showdata();


        return view;
    }



    private void insertCourseData() {
        String name = course_name.getText().toString();
        LecturerData lecturerData = new LecturerData(name);
        String key = userRef.push().getKey();
        userRef.child(key).setValue(name);
        course_name.setText("");
        spinnerList.clear();
        adapter.notifyDataSetChanged();
        myRef.push().setValue(lecturerData);
        //userRef.push().setValue(lecturerData);

        Toast.makeText(getContext(), "Data inserted", Toast.LENGTH_SHORT).show();

    }

    private void Showdata() {
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot item : snapshot.getChildren()) {
                    spinnerList.add(item.getValue().toString());
                }
            }
            //adapter.notifyDataSetChanged();

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.add_course_name:
                insertCourseData();
                break;
        }
    }
}