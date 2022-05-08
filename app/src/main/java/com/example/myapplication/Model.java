package com.example.myapplication;

public class Model {
    public Model(String image_Uri,String name) {
        this.image_Uri = image_Uri;
        this.name = name;
    }

    public String getImage_Uri() {
        return image_Uri;
    }

    public void setImage_Uri(String image_Uri) {
        this.image_Uri = image_Uri;
    }

    String image_Uri;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String name;
}
