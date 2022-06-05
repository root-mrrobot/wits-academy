package com.example.myapplication;

public class Rate {

    String rater;
    String course;
    float rating;

    public Rate(String rater, String course, float rating) {
        this.rater = rater;
        this.course = course;
        this.rating = rating;
    }


    public String getRater() {
        return rater;
    }

    public void setRater(String rater) {
        this.rater = rater;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

}
