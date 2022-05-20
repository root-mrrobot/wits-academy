package com.example.myapplication;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//imports for image

import android.app.ProgressDialog;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


import java.util.ArrayList;


public class LecturerHomeFragment extends Fragment implements View.OnClickListener {

    private FirebaseUser user;
    private DatabaseReference ref;
    private Uri filePath;

    private Button btnUPLOAD,btnCHOOSE;//three buttons on home page for lecturer add,choose,upload defined here
    private EditText course_name;
    private EditText course_desc;
    private String userID;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;
    private DatabaseReference userRef,descRef;
    private ArrayList<String> spinnerList;
    private ArrayAdapter<String> adapter;
    // instance for firebase storage and StorageReference
    FirebaseStorage storage;
    StorageReference storageReference;

    // Creating StorageReference and DatabaseReference object.
    StorageReference storageReference1;

    //creating spinner
    DatabaseReference databaseReference;
    Spinner spinner;
    String[] category = {"Health Science Faculty","Engineering Faculty", "Education Faculty","Commerce Faculty","Science Faculty"};
    ArrayAdapter<String>adapt;

    ImageView pickimage;




 public static  String category1000;

    ProgressDialog pd;
    View view;

    private StorageReference reference = FirebaseStorage.getInstance().getReference();
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference("Image");
    ImageView imageUri;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // initialising Firebase Auth to get Current user
        user = FirebaseAuth.getInstance().getCurrentUser();
        // Referencing Firebase Database to get Users
        ref = FirebaseDatabase.getInstance().getReference("Users");
        // assigning userID variable to get User ID
        if (!LecturerCourseInfoChecks.noUser(user)) {
            userID = user.getUid();
        }

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_lecturer_home, container, false);
        course_name = (EditText) view.findViewById(R.id.course_name);//reference to edit text in xml file for name of course
        course_desc = (EditText) view.findViewById(R.id.course_description);//reference to edit text in xml file for description of course
        btnCHOOSE = (Button) view.findViewById(R.id.btnChoose);//reference to button in xml file for choose button
        btnUPLOAD = (Button) view.findViewById(R.id.btnUpload); //reference to button in xml file for choose button

        // get the Firebase  storage reference
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        //used to create a title called lecturer created courses under a specific user who is a lecturer which will store names of courses created
        userRef = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Lecturer created courses");
        //used to create another branch in realtime database called courses to store course description and name once again
        myRef = FirebaseDatabase.getInstance().getReference()
                .child("courses");

        descRef = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Lecturer created courses");
        spinnerList = new ArrayList<>();//Array list to store course names created
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, spinnerList);

        btnCHOOSE.setOnClickListener(this);//making listeners active to check for button click
        btnUPLOAD.setOnClickListener(this);//making listeners active to check for button click
        Showdata();//calling show data function to show all data created by that user

        pd = new ProgressDialog(getContext());
        pd.setMessage("Uploading....");
        storageReference1 = FirebaseStorage.getInstance().getReference();

        //creating spinner with categories
        databaseReference = FirebaseDatabase.getInstance().getReference();
        spinner= view.findViewById(R.id.spinner);

        adapt = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,category);
        spinner.setAdapter(adapt);


        category1000 = spinner.getSelectedItem().toString();

        return view;

    }


    //main function behind choosing image from gallery
    ActivityResultLauncher<Intent> picactivity = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                //makes a listener to check for event using (registerForActivityResult)
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (LecturerCourseInfoChecks.successfulActivityResult(result)) {//if successful continue to get image
                        Intent data = result.getData();
                        pickimage = (ImageView) view.findViewById(R.id.imgView);//defining a reference for imageview
                        filePath = data.getData();//getting image
                        pickimage.setImageURI(filePath);//setting selected image to imageview
                    }
                }
            });


    // UploadImage method
    private void uploadImage(Uri uri) {

        if(uri != null) {
            pd.setTitle("Image is Uploading...");

            // Showing progressDialog.
            pd.show();

            final StorageReference fileRef = reference.child(System.currentTimeMillis() + "." + GetFileExtension(uri));
            fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {

                            // Referencing Firebase Database to get Users
                            ref = FirebaseDatabase.getInstance().getReference("Users/" + userID);
                            // This fetches the data from firebase
                            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    String id = userID;
                                    System.out.println(id);
                                    String name = course_name.getText().toString();
                                    category1000 = spinner.getSelectedItem().toString();//getting info that was entered from user
                                    String description = course_desc.getText().toString();//getting info that was entered from user
                                    LecturerData lecturerData = new LecturerData(name,description,category1000,uri.toString(), id);//constructor that deals with storing name and description for easier use
                                    String key = userRef.push().getKey();//getting a key for a specific user
                                    String key1 = myRef.getKey();
                                    String key2 = descRef.push().getKey();

                                    //statement to check if fields are empty than dont put values in firebase
                                    if (LecturerCourseInfoChecks.emptyString(name) || LecturerCourseInfoChecks.emptyString(description)){
                                        Toast.makeText(getContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();//pop up to display failure of due to empty strings insertion to database
                                    }
                                    //else if fields are filled put values in database
                                    else {
                                        userRef.child(key).setValue(name);
                                        //creates course name and description under lecturer created courses for that specific user
                                        course_name.setText("");//once button pressed clear fields
                                        course_desc.setText("");//once button pressed clear fields
                                        spinnerList.clear();//once button pressed clear spinner

                                        myRef.push().setValue(lecturerData);//push entered data to courses column shown desc,name
                                        Toast.makeText(getContext(), "Data inserted", Toast.LENGTH_SHORT).show();//pop up to display successful insertion to database
                                        pickimage.setImageResource(android.R.color.transparent);
                                    }

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    Toast.makeText(getActivity(), "Something wrong happened!", Toast.LENGTH_LONG).show();
                                }
                            });

                            Toast.makeText(getContext(), "Uploaded Successfully", Toast.LENGTH_SHORT).show();
                            pd.dismiss();

                        }
                    });
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                    pd.setTitle("Image is Uploading...");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Toast.makeText(getContext(), "Uploading Failed !!", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else{
            Toast.makeText(getContext(), "Please Select Image", Toast.LENGTH_LONG).show();
        }

    }


    // Creating Method to get the selected image file Extension from File Path URI.
    public String GetFileExtension(Uri uri) {

        ContentResolver contentResolver = getActivity().getContentResolver();

        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();

        // Returning the file Extension.
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;

    }

    //function that handles showing the data under the courses page
    private void Showdata() {
        //creating an event listener to show courses created when courses is clicked on navigation bar
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot item : snapshot.getChildren()) {//iterating through entered data by means of snapshot
                    spinnerList.add(item.getValue().toString());//adding entered data into spinnerList
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //do nothing here
            }
        });
    }

    //onclicklisteners goes through this main function for listeners to determine where to go.
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnChoose://choose button to go into gallery to select an image
                Intent imgintent = new Intent();//intent created and used to go into gallery
                imgintent.setAction(Intent.ACTION_PICK);//allows for picking image
                imgintent.setType("image/*");//storing which image is selected
                picactivity.launch(imgintent);//adding the image selected to imageview
                break;//after completion break

            case R.id.btnUpload:

                    uploadImage(filePath);

               // UploadImageFileToFirebaseStorage();
                break;
        }
    }
}
