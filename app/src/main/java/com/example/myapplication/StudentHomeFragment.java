package com.example.myapplication;

import android.app.ActionBar;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StudentHomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StudentHomeFragment extends Fragment {

    //  Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    //  Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StudentHomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StudentHomeFragment.
     */
    // Rename and change types and number of parameters
    public static StudentHomeFragment newInstance(String param1, String param2) {
        StudentHomeFragment fragment = new StudentHomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_student_home, container, false);

        getCourseDataFromFirebase(view);


        return view;

    }

    private void getCourseDataFromFirebase(View view)
    {
        LinearLayout layout = (LinearLayout) view.findViewById(R.id.studentHomeLinear);

        ArrayList<String> courses = new ArrayList<>();
        courses.add("Maths");
        courses.add("cs");
        courses.add("science");




        //final ArrayList<Course>[] courses2 = new ArrayList[]{new ArrayList<>()};

        DatabaseReference postRef = FirebaseDatabase.getInstance().getReference("courses/");

        postRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                ArrayList<Course> courses = new ArrayList<Course>();
                for(DataSnapshot child : snapshot.getChildren()) {
                    String id = child.getKey();
                    String name = child.child("name").getValue().toString();

                    Course course = new Course();

                    course.setName(name);
                    course.setId(id);
                    courses.add(course);


                }

                //TODO any function that displays firebase course data on UI must be called from
                // here to prevent Null exceptions
                createCoursesTextviews(layout, courses);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("The read failed: " + error.getMessage());
            }
        });


    }

    //  createCoursesTextviews creates a very basic UI consisting of textviews displaying course
    //  data on a linear layout. To create a proper UI this function can be completely changed
    //  or replaced by a more suitable function.
    //  Any function that displays course data from firebase on the UI (such as createCoursesTextviews)
    //  must be called from within the onDataChange function which is in getCourseDataFromFirebase() as
    //  the data is being displayed in real time.
    //  (location marked with a "TODO" )


    private void createCoursesTextviews(LinearLayout layout, ArrayList<Course> courses)
    {

        // iterates through list of courses and creates + displays textview for each
        for (int i = 0; i < courses.size(); i++)
        {
            Course course = courses.get(i);

            String name = course.getName();
            String id = course.getId();
            String category = course.category;
            String description = course.description;


            TextView courseName = new TextView(getContext());
            courseName.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT));
            courseName.setText(name);
            layout.addView(courseName);

            TextView courseId = new TextView(getContext());
            courseId.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT));
            courseId.setText(id);
            layout.addView(courseId);

            TextView courseCategory = new TextView(getContext());
            courseCategory.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT));
            courseCategory.setText(category);
            layout.addView(courseCategory);

            TextView courseDescription = new TextView(getContext());
            courseDescription.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT));
            courseDescription.setText(description);
            layout.addView(courseDescription);


        }


    }


}
