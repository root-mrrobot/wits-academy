package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    private Button registerUser;
    private TextView login;
    private EditText editTextFullName, editTextEmail, editTextPassword, editTextConfirmPassword;
    private CheckBox showPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // will hide the title
        getSupportActionBar().hide(); // hide the title bar
        setContentView(R.layout.activity_register);

        mAuth=FirebaseAuth.getInstance();
        login = findViewById(R.id.loginText);
        login.setOnClickListener(this);
        registerUser = findViewById(R.id.btnRegister);
        editTextFullName =  findViewById(R.id.fullName) ;
        editTextEmail =  findViewById((R.id.email));
        editTextPassword = findViewById((R.id.password));
        editTextConfirmPassword = findViewById((R.id.confirmPassword));
        registerUser.setOnClickListener(this);
        showPassword = (CheckBox) findViewById(R.id.showRegisterPassword);

        showPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Show Password
                    editTextPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    editTextConfirmPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else {
                    // Hide Password
                    editTextPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    editTextConfirmPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.loginText:
                startActivity(new Intent(this, Login.class));
                break;
            case R.id.btnRegister:
                registerUser();
                break;
        }
    }

    private void registerUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String fullName = editTextFullName.getText().toString().trim();
        String confirmPassword = editTextConfirmPassword.getText().toString().trim();

        if(!RegisterValidation.notEmptyString(fullName)){
            editTextFullName.setError("Full name required");
            editTextFullName.requestFocus();
            return;
        }
        if(!RegisterValidation.notEmptyString(email)){
            editTextEmail.setError("Email required");
            editTextEmail.requestFocus();
            return;
        }

        if(!RegisterValidation.matchesEmailPattern(email)){
            editTextEmail.setError("Valid Email Required");
            editTextEmail.requestFocus();
            return;
        }

        if((!RegisterValidation.notEmptyString(password)) && (!RegisterValidation.lengthGreaterThanSix(password))){
            editTextPassword.setError("Invalid password");
            editTextPassword.requestFocus();
            return;
        }

        if (!RegisterValidation.passwordMatchesConfirm(password, confirmPassword)) {
            editTextConfirmPassword.setError("Passwords do not match!");
            editTextConfirmPassword.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            User user = new User(fullName,email);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(Register.this,"user registration succesful",Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(Register.this, Home.class));
                                    }
                                    else{
                                        Toast.makeText(Register.this,"Failed",Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }
                    }
                });
    }


}
