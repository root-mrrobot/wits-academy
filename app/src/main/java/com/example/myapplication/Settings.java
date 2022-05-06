package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Settings extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference ref;

    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // initialising Firebase Auth to get Current user
        user = FirebaseAuth.getInstance().getCurrentUser();

        // assigning userID variable to get User ID
        if (user != null) {
            userID = user.getUid();
        }

        final TextView nameTextView = findViewById(R.id.settingsName);

        // Referencing Firebase Database to get Users
        ref = FirebaseDatabase.getInstance().getReference("Users/" + userID);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String val = snapshot.child("fullName").getValue(String.class);
                nameTextView.setText(val);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Settings.this, "Something wrong happened!", Toast.LENGTH_LONG).show();
            }
        });
    }
}