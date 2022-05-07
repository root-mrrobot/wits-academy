package com.example.myapplication;


// This class allows for the creation and use of course objects
class Course {
    String id;
    String name;
    String description;
    String category;
    String teacher;
    double rating;

    // the following variables will have dummy values until fields for them are added to firebase
    String image = "this will be an image";                         // place holder until images are supported



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String name) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String name) {
        this.name = description;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
