package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Random;


public class StudentHomeFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    ListView myListCourses;
    ArrayList<Course> myArrayList = new ArrayList<Course>();
    ArrayList<String> c = new ArrayList<String>();
    ArrayList<String> fullC = new ArrayList<String>();
    DatabaseReference coursesRef;
    DatabaseReference userRef;
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
        spinner.setSelection(0);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),R.array.Categories_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        myListCourses=(ListView) view.findViewById(R.id.listView);
        return view;
    }

    public void display(){
        ArrayAdapter<String> myArrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,c);

        // Get the reference to the courses from the real-time database
        coursesRef= FirebaseDatabase.getInstance().getReference("courses/");
        coursesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot child : snapshot.getChildren()) {

                    // Retrieve all the necessary information from the real-time database
                    String id = child.getKey();
                    String name = child.child("name").getValue().toString();
                    String description = child.child("description").getValue().toString();
                    String category = child.child("courseCategory").getValue().toString();
                    String lecID = child.child("lecID").getValue().toString();
                    String image = child.child("imageUri").getValue().toString();
                    float rating = r[new Random().nextInt(r.length)];

                    userRef = FirebaseDatabase.getInstance().getReference("Users/" + lecID);
                    // This fetches the data from firebase
                    userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String lecName = snapshot.child("fullName").getValue(String.class);

                            /* If a category filter is applied, add the courses that are in that category to myArrayList
                             * Then create a string that contains all the relevant information for each course
                             * and add each string to the arrayList c
                             */
                            if (category.equals(spinner.getSelectedItem().toString())) {
                                Course course = new Course();



                                course.setName(name);
                                course.setDescription((description));
                                course.setCategory(category);
                                course.setId(id);
                                course.setRating(rating);
                                course.setTeacher(lecName);
                                course.setImage(image);
                                myArrayList.add(course);

                                String singleCourse = "Course Name: " + name + "\nTeacher: " + lecName + "\nRating: " + rating;
                                c.add(singleCourse);
                            }

                            /* If the all courses filter is applied, add all the courses to myArrayList
                       * Then create a string that contains all the relevant information for each course
                       * and add each string to the arrayList c
                      */
                            else if (spinner.getSelectedItem().toString().equals("All Courses")){
                                Course course = new Course();

                                course.setName(name);
                                course.setDescription((description));
                                course.setCategory(category);
                                course.setId(id);
                                course.setRating(rating);
                                course.setTeacher(lecName);
                                course.setImage(image);
                                myArrayList.add(course);

                                String fullCourse = id + "\n" + name + "\n" + category + "\n" + description + "\n" + rating + "\n" + lecName + "\n" + image;
                                fullC.add(fullCourse);

                                String singleCourse =  "Course Name: " + name + "\nTeacher: " + lecName + "\nRating: " + rating;
                                c.add(singleCourse);

                            }

                            // display courses using adapter
                            myArrayAdapter.notifyDataSetChanged();
                            myListCourses.setAdapter(myArrayAdapter);

                            // open the popup activity and display the course information according to what was clicked on
                            myListCourses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                                    // get the course information of the selected course and enter it into an array
                                    String course = fullC.get(position);
                                    String[] splitCourse = course.split(":|\n");

                                    // get the necessary information from the above array
                                    String courseName = splitCourse[1];
                                    //String courseId = splitCourse[];
                                    String category = splitCourse[2];
                                    String description = splitCourse[3];
                                    String rating = splitCourse[4];
                                    String lecturerName = splitCourse[5];
                                    String courseImage = splitCourse[6] + ":"+ splitCourse[7];

                                    // create a bundle called extras
                                    Bundle extras = new Bundle();

                                    // add all the information that needs to be imported to popup to the extras bundle
                                    extras.putString("courseName", courseName);
                                    extras.putString("courseDescription", description);
                                    extras.putString("category", category);
                                    extras.putString("rating", rating);
                                    extras.putString("lecturerName", lecturerName);
                                    extras.putString("image", courseImage);

                        /* create an intent to go from the current page to the popup and carry over the
                        extras to be used on the popup
                         */
                                    Intent intent = new Intent(view.getContext(), StudentCoursePopUp.class);
                                    intent.putExtras(extras);
                                    startActivity(intent);
                                }
                            });
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
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