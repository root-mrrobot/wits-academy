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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register extends AppCompatActivity implements View.OnClickListener {
    //creating variables to use for reference
    private FirebaseAuth mAuth;
    private Button registerUser,LETSGOO;
    private TextView login;
    private EditText editTextFullName, editTextEmail, editTextPassword, editTextConfirmPassword;
    private CheckBox showPassword;
    TextView email;
    FirebaseUser fAuth;
    DatabaseReference fdata;
    String userId;
    public String unitTest = "True";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // will hide the title
        getSupportActionBar().hide(); // hide the title bar
        setContentView(R.layout.activity_register);

        //initialising constants
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
        email = findViewById(R.id.email);
        fAuth = FirebaseAuth.getInstance().getCurrentUser() ;
        if (fAuth != null) {
            userId = fAuth.getUid();
        }
        fdata = FirebaseDatabase.getInstance().getReference();



        //show password function
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

    //when register button pressed authenticates user and sends user to home page
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.loginText:
                startActivity(new Intent(this, Login.class));//not a member?go back to login
                break;
            case R.id.btnRegister:
                registerUser();
                break;

//            case R.id.btn_straightLecturer:
//                startActivity(new Intent(getApplicationContext(), LecturerHomeFragment.class));
//                break;
        }
    }

    //authentication of credentials entered on register page
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


        //firebase function to store user credentials in realtime database
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            User user = new User(fullName,email,password);//varaiables that are stored in database.

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(Register.this,"User Registration Successful!",Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(Register.this, Home.class));
                                    }
                                    else{
                                        Toast.makeText(Register.this,"Registration failed - Please try again",Toast.LENGTH_LONG).show();
                                        unitTest ="False";
                                    }
                                }
                            });
                        }
                    }
                });

        //getting email from database to compare with email entered(if exists already)
        mAuth.fetchSignInMethodsForEmail(email)
                .addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                    @Override
                    public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {

                        boolean isNewUser = task.getResult().getSignInMethods().isEmpty();

                        if (isNewUser) {
                            //creates the new user will run through the previous function.
                        } else {
                            //if email already exists user not created must change email
                            Toast.makeText(Register.this,"Email already exists",Toast.LENGTH_LONG).show();
                            Toast.makeText(Register.this,"Please enter another email",Toast.LENGTH_LONG).show();
                        }

                    }
                });





    }
}
