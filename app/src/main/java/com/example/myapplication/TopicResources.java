package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TopicResources extends AppCompatActivity{

    Button get_pdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_resources);
        get_pdf = findViewById(R.id.btn_getPdf);

        get_pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retrievePdf();

            }
        });
    }

    public void retrievePdf()
    {

        String uri = "https://firebasestorage.googleapis.com/v0/b/ctrlaltelite-f0d01.appspot.com/o/upload1653007754301.pdf?alt=media&token=f1489156-a782-48a8-8adc-ec9340480ed8";
        Intent intent = new Intent (Intent.ACTION_VIEW);
        intent.setType("application/pdf");
        intent.setData(Uri.parse(uri));
        startActivity(intent);
    }


}