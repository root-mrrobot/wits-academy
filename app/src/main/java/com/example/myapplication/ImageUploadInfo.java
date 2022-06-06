package com.example.myapplication;

public class ImageUploadInfo {


    public String courseName;
    public String imageURL;

//    public ImageUploadInfo() {
//
//    }

    public ImageUploadInfo(String name,String url) {
       this.courseName = name;
        this.imageURL= url;
    }
    public String getImageName() {
        return courseName;
    }


    public String getImageURL() {
        return imageURL;
    }
}
