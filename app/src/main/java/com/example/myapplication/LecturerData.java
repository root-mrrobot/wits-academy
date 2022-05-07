package com.example.myapplication;

public class LecturerData {
    String name;
    String description;

    String category1000;


    public void setDescription(String description) {
        this.description = description;
    }

    public LecturerData(String name, String description, String category1000) {
        this.name = name;
        this.description = description;
        this.category1000=category1000;


    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public String getcourseCategory(){return category1000;}
}

