package com.example.myapplication;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Settings extends AppCompatActivity implements View.OnClickListener {

    private FirebaseUser user;
    private DatabaseReference ref;
    ProgressDialog pd1;
    private String userID;
    private StorageReference reference_pic = FirebaseStorage.getInstance().getReference();
    ImageView Profilepickimage;
    private Uri ProfilefilePath;
    private DatabaseReference Profile_picRef,ref1;
    private Button upload_pic,update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Profile_picRef = FirebaseDatabase.getInstance().getReference()
                .child("Profile pictures");

        pd1 = new ProgressDialog(Settings.this);
        pd1.setMessage("Uploading....");

        upload_pic = findViewById(R.id.profilepic_uploadBtn);
        update = findViewById(R.id.btnChangePw);//change the id here
        upload_pic.setOnClickListener(this);
        update.setOnClickListener(this);


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


    //main function behind choosing image from gallery
    ActivityResultLauncher<Intent> profilePicactivity = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                //makes a listener to check for event using (registerForActivityResult)
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {//if successful continue to get image
                        Intent data = result.getData();
                        Profilepickimage = (ImageView) findViewById(R.id.editProfilePic);//defining a reference for imageview
                        ProfilefilePath = data.getData();//getting image
                        Profilepickimage.setImageURI(ProfilefilePath);//setting selected image to imageview
                    }
                }
            });


    // UploadImage method
    private void uploadImage(Uri uri) {

        if(uri != null) {
            pd1.setTitle("Image is Uploading...");

            // Showing progressDialog.
            pd1.show();

            final StorageReference picfileRef = reference_pic.child(System.currentTimeMillis() + "." + GetFileExtension(uri));
            picfileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    picfileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            System.out.println("test reached");
                            // Referencing Firebase Database to get Users
                            ref1 = FirebaseDatabase.getInstance().getReference("Users/" + userID );
                            // This fetches the data from firebase
                            ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    System.out.println("test reached");

                                    String val = snapshot.child("email").getValue(String.class);
                                    profilePic_data pdata = new profilePic_data(uri.toString(),val);//constructor that deals with storing name and description for easier use



                                    //else if fields are filled put values in database



                                        Profile_picRef.push().setValue(pdata);//push entered data to courses column shown desc,name
                                        Toast.makeText(getApplicationContext(), "Data inserted", Toast.LENGTH_SHORT).show();//pop up to display successful insertion to database
                                       // Profilepickimage.setImageResource(android.R.color.transparent);


                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    Toast.makeText(getApplicationContext(), "Something wrong happened!", Toast.LENGTH_LONG).show();
                                }
                            });

                            Toast.makeText(getApplicationContext(), "Uploaded Successfully", Toast.LENGTH_SHORT).show();
                            pd1.dismiss();

                        }
                    });
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                    pd1.setTitle("Image is Uploading...");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Toast.makeText(getApplicationContext(), "Uploading Failed !!", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else{
            Toast.makeText(getApplicationContext(), "Please Select Image", Toast.LENGTH_LONG).show();
        }

    }


    public String GetFileExtension(Uri uri) {

        ContentResolver contentResolver = getApplicationContext().getContentResolver();

        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();

        // Returning the file Extension.
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.profilepic_uploadBtn://choose button to go into gallery to select an image
                Intent imgintent = new Intent();//intent created and used to go into gallery
                imgintent.setAction(Intent.ACTION_PICK);//allows for picking image
                imgintent.setType("image/*");//storing which image is selected
                profilePicactivity.launch(imgintent);//adding the image selected to imageview
                break;//after completion break

            case R.id.btnChangePw:

                uploadImage(ProfilefilePath);

                // UploadImageFileToFirebaseStorage();
                break;
        }
    }
}