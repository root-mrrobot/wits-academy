package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class Account extends AppCompatActivity implements View.OnClickListener {
    
    // Logout button

    private Button logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        logout =  findViewById(R.id.btnLogout);
        logout.setOnClickListener(this);
    }
    
    // Takes user back to login page

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnLogout:
                startActivity(new Intent(this, Login.class));
                break;
        }
    }
}
