package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private TextView register, forgot_pass;
    private EditText editTextEmail, editTextPassword;
    private Button signIn;
    private CheckBox showPassword;
    private FirebaseAuth mAuth;
    private SignInButton googleSignIn;
    private GoogleSignInClient mGoogle;
    private GoogleSignInOptions gso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // will hide the title
        getSupportActionBar().hide(); // hide the title bar
        setContentView(R.layout.activity_login);
        register = findViewById(R.id.registerText);
        register.setOnClickListener(this);
        signIn =  findViewById(R.id.buttonLogin);
        forgot_pass = findViewById((R.id.forgotPassword));
        signIn.setOnClickListener(this);
        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        showPassword = findViewById(R.id.showLoginPassword);
        mAuth=FirebaseAuth.getInstance();
        googleSignIn = findViewById(R.id.googleSignInButton);
        googleSignIn.setOnClickListener(this);

        showPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Show Password
                    editTextPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else {
                    // Hide Password
                    editTextPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });


        //creating a listener to react to click of forgot password
        forgot_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //which email the user wants his reset option sent to
                EditText reset_email = new EditText(view.getContext());
                //creating a pop up to reset password
                AlertDialog.Builder password_reset_dialog = new AlertDialog.Builder(view.getContext());
                password_reset_dialog.setTitle("Reset Password?");
                password_reset_dialog.setMessage("Enter your email to receive reset password link");
                password_reset_dialog.setView(reset_email);

                //creating yes and no buttons for pop up
                password_reset_dialog.setPositiveButton("Send", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //getting email entered to send link
                        String mail = reset_email.getText().toString();
                        //if email link sent succesfully
                        mAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(Login.this,"Please check your email for reset link",Toast.LENGTH_LONG).show();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Login.this,"Error! email link not sent" + e.getMessage(),Toast.LENGTH_LONG).show();

                            }
                        });
                    }
                });

                password_reset_dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //close pop up and go back to login

                    }
                });

                password_reset_dialog.create().show();// show the pop up
            }
        });




    }

    //creating sign in method for listener
    private void signIn(){
        Intent signInIntent = mGoogle.getSignInIntent();
        startActivityForResult(signInIntent,1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                task.getResult(ApiException.class);
                finish();
                Intent intent = new Intent(Login.this,Home.class);
                startActivity(intent);
            } catch (ApiException e) {
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void onClick(View view) {
        switch(view.getId()){

            case R.id.registerText:
                startActivity(new Intent(this, Register.class));
                break;

            case R.id.buttonLogin:
                userLogin();
                break;

            case R.id.googleSignInButton:
                googleSignIn();
                break;

        }
    }


    private void userLogin() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(!LoginValidation.notEmptyString(email)){
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }
        if(!LoginValidation.matchesEmailPattern(email)){
            editTextEmail.setError("Please enter a valid email");
            return;
        }
        if(!LoginValidation.notEmptyString(password)){
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }
        if(!LoginValidation.lengthGreaterThanSix(password)){
            editTextPassword.setError("Password too short");
            editTextPassword.requestFocus();
            return;
        }
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(Login.this, Home.class));
                }
                else{
                    Toast.makeText(Login.this,"Failed to login, please check your credentials",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void googleSignIn()
    {
        //creating google sign in method
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogle = GoogleSignIn.getClient(this,gso);

        //setting listener for when google button clicked
        GoogleSignInClient googleSignInClient= GoogleSignIn.getClient(this,gso);
        googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){

                    FirebaseAuth.getInstance().signOut(); // very important if you are using firebase.
                    Intent login_intent = new Intent(getApplicationContext(),Home.class);
                    login_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK); // clear previous task (optional)
                    startActivity(login_intent);

                }
            }
        });


    }



}

