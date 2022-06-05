package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LecturerAccountFragment extends Fragment implements View.OnClickListener{

    // Firebase variables
    private FirebaseUser user;
    private DatabaseReference ref,ref1;
    private String userID;

    // Buttons, Toggles TextView and View variables
    private SwitchCompat switchBtn;
    private ToggleButton studentTeacherToggleBtn;
    private TextView studentTextView;
    private Button logout;
    private Button settings;
    View view;
    ImageView pro_pic;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_lecturer_account, container, false);

        // Toggle Button things
        studentTeacherToggleBtn = view.findViewById(R.id.toggleBtn2);
        studentTeacherToggleBtn.setOnClickListener(this);
        // Text variable of Student View
        studentTextView = view.findViewById(R.id.studentTextView);
        pro_pic = view.findViewById(R.id.profilePic2);

        studentTeacherToggleBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    // Changed Text View to 'Teacher View' to show option to change to
                    studentTextView.setText(R.string.studentTextView);
                    // Toast Message to say successful change of View to Student View
                    Toast.makeText(getActivity(), "You have successfully changed to Teacher View", Toast.LENGTH_SHORT).show();
                }
                else {
                    // Changed Text View to 'Teacher View'
                    //studentTeacherTextView.setText(R.string.studentTextView);
                    // Toast Message to say successful change of View to Teacher View
                    Toast.makeText(getActivity(), "You have successfully changed to Student View", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Referencing logout button from xml file
        logout = view.findViewById(R.id.btnLogout2);

        // setting on Click Listener
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Alert Dialog init
                AlertDialog.Builder logout_dialog = new AlertDialog.Builder(view.getContext());
                // Title of Alert Dialog
                logout_dialog.setTitle("Log out of Wits Academy");
                // Message of Dialog
                logout_dialog.setMessage("Are you sure you would like to log out?");

                // creating yes and no buttons for pop up
                logout_dialog.setPositiveButton("Log out", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Sending user to Login Page - User has logged out
                        Intent intent = new Intent(getActivity(), Login.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        Toast.makeText(getActivity(),"You have successfully logged out. Please come back again!",Toast.LENGTH_SHORT).show();
                    }
                });

                logout_dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Close pop up and go back to account page
                    }
                });

                logout_dialog.create().show(); // Show the pop up
            }
        });

        // Referencing settings button from xml file
        settings = view.findViewById(R.id.btnSettings2);
        // setting on Click Listener
        settings.setOnClickListener(this);

        // initialising Firebase Auth to get Current user
        user = FirebaseAuth.getInstance().getCurrentUser();
        // Referencing Firebase Database to get Users
        ref = FirebaseDatabase.getInstance().getReference("Users");
        // assigning userID variable to get User ID
        if (user != null) {
            userID = user.getUid();
        }

        final TextView nameTextView = view.findViewById(R.id.profileName2);
        final TextView emailTextView = view.findViewById(R.id.profileEmail2);

        // Referencing Firebase Database to get Users
        ref = FirebaseDatabase.getInstance().getReference("Users/" + userID);
        // This fetches the data from firebase
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Getting the name from Firebase
                String nameVal = snapshot.child("fullName").getValue(String.class);
                // Setting textView to fetched name
                nameTextView.setText(nameVal);

                // Getting the email from Firebase
                String emailVal = snapshot.child("email").getValue(String.class);
                // Setting textView to fetched email
                emailTextView.setText(emailVal);

                ref1 = FirebaseDatabase.getInstance().getReference("Profile pictures/" );




                // This fetches the data from firebase
                ref1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot child : snapshot.getChildren()) {

                            //get the child of the course that we clicked on
                            String usersTableName = child.child("email_").getValue().toString();

                            // loading that data into rImiage
                            //Displays the courses information into the TextViews and retrieves the image associated with it
                            if (usersTableName.equals(emailVal))
                            {

                                System.out.println("email " + emailVal);

                                String link = child.child("profilePic_URI").getValue().toString();

                                // variable which is ImageView
                                Glide.with(getActivity()).load(link).into(pro_pic);

                            }

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Error Toast Message
                Toast.makeText(getActivity(), "Something wrong happened!", Toast.LENGTH_LONG).show();
            }
        });



        return view;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnSettings2:
                // Sending to Settings Page
                Intent intentSettings = new Intent(getActivity(), Settings.class);
                startActivity(intentSettings);
                break;

            case R.id.toggleBtn2:
                // Sending to Student View
                Intent intentStud = new Intent(getActivity(), StudentNavigation.class);
                intentStud.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intentStud);
                break;
        }
    }
}