package com.example.myapplication;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//imports for image
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;




import java.util.ArrayList;


public class LecturerHomeFragment extends Fragment implements View.OnClickListener {
    // views for button
    private Button btnSelect, btnUpload;
    // view for image view
    private ImageView imageView;
    // Uri indicates, where the image will be picked from
    private Uri filePath;
    // request code
    private final int PICK_IMAGE_REQUEST = 22;
    // instance for firebase storage and StorageReference
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private DatabaseReference imgRef;
    private Button btnAddToDatabase,btnUPLOAD,btnCHOOSE;//three buttons on home page for lecturer add,choose,upload defined here
    private EditText course_name;
    private EditText course_desc;
    private EditText lec_name;
    private static final String TAG = "AddToDatabase";
    private String userID;
    private ImageView image_View;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;
    private DatabaseReference userRef,descRef;
    private String categories;
    private DatabaseReference spinnerRef;
    private ArrayList<String> spinnerList;
    private ArrayAdapter<String> adapter;
    private TextView my_courses;
    // instance for firebase storage and StorageReference
    FirebaseStorage storage;
    StorageReference storageReference;
    // Folder path for Firebase Storage.
    String Storage_Path = "All_Image_Uploads/";

    // Root Database Name for Firebase Database.
    String Database_Path = "All_Image_Uploads_Database";

    // Creating StorageReference and DatabaseReference object.
    StorageReference storageReference1;

    //creating spinner
    DatabaseReference databaseReference;
    Spinner spinner;
   String[] category = {"Health Science Faculty","Engineering Faculty", "Education Faculty","Commerce Faculty","Science Faculty"};
ArrayAdapter<String>adapt;
 public static  String category1000;

    ProgressDialog pd;
    View view;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_lecturer_home, container, false);
        course_name = (EditText) view.findViewById(R.id.course_name);//reference to edit text in xml file for name of course
        course_desc = (EditText) view.findViewById(R.id.course_description);//reference to edit text in xml file for description of course
        btnAddToDatabase = (Button) view.findViewById(R.id.add_course_name);////reference to button  in xml file for add button
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
        spinnerList = new ArrayList<>();//Array list to store cousre names created
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, spinnerList);
        btnAddToDatabase.setOnClickListener(this);//making listeners active to check for button click
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
                    if (result.getResultCode() == Activity.RESULT_OK) {//if successful continue to get image
                        Intent data = result.getData();
                        ImageView pickimage = (ImageView) view.findViewById(R.id.imgView);//defining a reference for imageview
                        filePath = data.getData();//getting image
                        pickimage.setImageURI(filePath);//setting selected image to imageview
                    }
                }
            });


    // UploadImage method
    private void uploadImage() {
        if(filePath != null) {
            pd.show();
            StorageReference storageRef = storage.getReferenceFromUrl("gs://ctrlaltelite-f0d01.appspot.com");

            StorageReference childRef = storageRef.child("image.jpg");

            //uploading the image
            UploadTask uploadTask = childRef.putFile(filePath);

            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    pd.dismiss();
                    Toast.makeText(getContext(), "Upload successful", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    pd.dismiss();
                    Toast.makeText(getContext(), "Upload Failed -> " + e, Toast.LENGTH_SHORT).show();
                }
            });
        }
        else {
            Toast.makeText(getContext(), "Select an image", Toast.LENGTH_SHORT).show();
        }

    }



    // Creating Method to get the selected image file Extension from File Path URI.
    public String GetFileExtension(Uri uri) {

        ContentResolver contentResolver = getActivity().getContentResolver();

        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();

        // Returning the file Extension.
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;

    }


    // Creating UploadImageFileToFirebaseStorage method to upload image on storage.
    public void UploadImageFileToFirebaseStorage() {

        // Checking whether FilePathUri Is empty or not.
        if (filePath != null) {

            // Setting progressDialog Title.
            pd.setTitle("Image is Uploading...");

            // Showing progressDialog.
            pd.show();

            // Creating second StorageReference.
            StorageReference storageReference2nd = storageReference1.child(Storage_Path + System.currentTimeMillis() + "." + GetFileExtension(filePath));

            //creating a ref to get image URI
            final StorageReference fileRef = storageReference1.child(System.currentTimeMillis() + "." + GetFileExtension(filePath));
            // Adding addOnSuccessListener to second StorageReference.
            storageReference2nd.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // Getting image name from EditText and store into string variable.
                            course_name = view.findViewById(R.id.course_name);
                            String name = course_name.getText().toString().trim();



                            // Hiding the progressDialog after done uploading.
                            pd.dismiss();

                            // Showing toast message after done uploading.
                            Toast.makeText(getContext(), "Image Uploaded Successfully ", Toast.LENGTH_LONG).show();

                        }
                    })
                    // If something goes wrong .
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {

                            // Hiding the progressDialog.
                            pd.dismiss();

                            // Showing exception erro message.
                            Toast.makeText(getContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })

                    // On progress change upload time.
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                            // Setting progressDialog Title.
                            pd.setTitle("Image is Uploading...");

                        }
                    });
        }
        else {

            Toast.makeText(getContext(), "Please Select Image", Toast.LENGTH_LONG).show();

        }
    }







//function to handle with data or no data being entered on home page
    private void insertCourseData() {
        String name = course_name.getText().toString();
        category1000 = spinner.getSelectedItem().toString();//getting info that was entered from user
        String description = course_desc.getText().toString();//getting info that was entered from user
        LecturerData lecturerData = new LecturerData(name,description,category1000);//constructor that deals with storing name and description for easier use
        String key = userRef.push().getKey();//getting a key for a specific user
        String key2 = descRef.push().getKey();
        //statement to check if fields are empty than dont put values in firebase
        if (name.equals("") || description.equals("")){
            Toast.makeText(getContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();//pop up to display failure of due to empty strings insertion to database
        }
        //else if fields are filled put values in database
        else {
            userRef.child(key).setValue(name);
           //creates course name under lecturer created courses for that specific user
            //descRef.child(key2).setValue(description);//creates course description under lecturer created courses for that specific user
            course_name.setText("");//once button pressed clear fields
            course_desc.setText("");//once button pressed clear fields
           spinnerList.clear();//once button pressed clear spinner
//            adapter.notifyDataSetChanged();//notifications for change in data
            myRef.push().setValue(lecturerData);//push entered data to courses column shown desc,name
            Toast.makeText(getContext(), "Data inserted", Toast.LENGTH_SHORT).show();//pop up to display successful insertion to database
        }
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



    //onclicklisteners goes through this main function for listeners to determine where to go
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.add_course_name://add button goes through insertcoursedata function to add data to firebase
                insertCourseData();
                break;//after completion break

            case R.id.btnChoose://choose button to go into gallery to select an image
                Intent imgintent = new Intent();//intent created and used to go into gallery
                imgintent.setAction(Intent.ACTION_PICK);//allows for picking image
                imgintent.setType("image/*");//storing which image is selected
                picactivity.launch(imgintent);//adding the image selected to imageview
                break;//after completion break

            case R.id.btnUpload:
               // uploadImage();
                UploadImageFileToFirebaseStorage();
                break;
        }
    }



}