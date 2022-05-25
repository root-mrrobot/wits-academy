package com.example.myapplication;


// This class allows for the creation and use of course objects
public class Course {

    String id;
    String name;
    String description;
    String category;
    String teacher;
    String image;
    double rating;

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

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Course(String id, String name, String description, String category, String teacher, String image, double rating)
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.teacher = teacher;
        this.image = image;
        this.rating = rating;
    }
}
