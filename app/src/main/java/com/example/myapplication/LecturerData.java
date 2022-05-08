package com.example.myapplication;

import com.google.firebase.database.DatabaseReference;

public class LecturerData {
    String name;
    String description;

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    String imageUri;
String lecName;



    DatabaseReference image_uri;
    String category1000;

//adding category to database via constructor
    public void setDescription(String description) {
        this.description = description;
    }

    public String getLecName() {
        return lecName;
    }

    public void setLecName(String lecName) {
        this.lecName = lecName;
    }

    public LecturerData(String name, String description, String category1000, String imageUri, String lecName) {
        this.name = name;
        this.description = description;
        this.category1000=category1000;
        this.imageUri=imageUri;
        this.lecName = lecName;

    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public String getcourseCategory(){return category1000;}
}

