package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class StudentNavigation extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_navigation);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        //sets home fragment as first fragment set on when on student view
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new StudentHomeFragment()).commit();
        bottomNavigationView.setSelectedItemId(R.id.home);

        //changes fragments based on which button in navigation bar is selected
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()){
                    case R.id.home:
                        fragment = new StudentHomeFragment();
                        break;
                    case R.id.account:
                        fragment = new StudentAccountFragment();
                        break;
                    case R.id.subsciptions:
                        fragment = new StudentSubscriptionsFragment();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment).commit();

                return true;
            }
        });

    }
}