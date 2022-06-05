package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class lecUploadPdf extends AppCompatActivity {
    StorageReference storageReference;
    DatabaseReference pdfRef;
    EditText pdf;
    Button btn_pdf, exit;
    String subject_name,Topic_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lec_upload_pdf);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        subject_name = LecturerCoursesFragment.courseNameClicked;
        Topic_name = ScrollLecturerCourse.topicNameClicked;
        pdf = findViewById(R.id.txt_pdf);
        btn_pdf = findViewById(R.id.btn_pdf);
        exit = findViewById(R.id.btnExit);
        pdfRef = FirebaseDatabase.getInstance().getReference("pdfUploads");
        btn_pdf.setEnabled(false);
        storageReference= FirebaseStorage.getInstance().getReference();
        //creating onclickListener for uploading pdf
        pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PdfSelect();
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LecturerResourceView.class));
            }
        });
    }


    private void PdfSelect() {
        Intent intent = new Intent();//intent to pick the file from phone
        intent.setType("application/pdf");
        intent.setAction(intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"PDF FILE SELECT"),101);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==101 && resultCode==RESULT_OK && data!=null && data.getData()!=null)
        {


            btn_pdf.setEnabled(true);
            pdf.setText(data.getDataString().substring(data.getDataString().lastIndexOf("/")+1));
            btn_pdf.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UploadPDF(data.getData());
                }
            });

        }

    }

    private void UploadPDF(Uri data) {
        final ProgressDialog pd1 = new ProgressDialog(this);
        pd1.setTitle("File is uploading");
        pd1.show();
        StorageReference ref = storageReference.child("upload"+System.currentTimeMillis()+".pdf");
        ref.putFile(data)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                        while(!uriTask.isComplete());
                        Uri uri = uriTask.getResult();


                        Pdf_data pdfData =new Pdf_data(pdf.getText().toString(),uri.toString(),subject_name,Topic_name);
                        pdfRef.child(pdfRef.push().getKey()).setValue(pdfData);
                        Toast.makeText(getApplicationContext(),"File is uploaded",Toast.LENGTH_LONG).show();
                        pd1.dismiss();
                        pdf.setText("");


                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                double progress = (100.0* snapshot.getBytesTransferred())/snapshot.getTotalByteCount();
                pd1.setMessage("File Uploaded..." + (int) progress + "%");

            }
        });
    }
}