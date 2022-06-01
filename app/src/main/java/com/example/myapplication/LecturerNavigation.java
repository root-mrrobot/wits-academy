package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class LecturerNavigation extends AppCompatActivity {

    BottomNavigationView bottomNavigationView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecturer_navigation);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        bottomNavigationView1 = findViewById(R.id.bottomNavigationView1);

        //sets home fragment as first fragment when logged in as a lecturer
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout1, new LecturerHomeFragment()).commit();
        bottomNavigationView1.setSelectedItemId(R.id.lecturerHome);

        //changes fragments based on which button in navigation bar is selected
        bottomNavigationView1.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()){
                    case R.id.lecturerHome:
                        fragment = new LecturerHomeFragment();
                        break;
                    case R.id.account:
                        fragment = new LecturerAccountFragment();
                        break;
                    case R.id.courses:
                        fragment = new LecturerCoursesFragment();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout1, fragment).commit();

                return true;
            }
        });

    }
}