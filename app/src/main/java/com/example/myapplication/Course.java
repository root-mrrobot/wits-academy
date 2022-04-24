package com.example.myapplication;


// This class allows for the creation and use of course objects
class Course {
    String id;
    String name;

    // the following variables will have dummy values until fields for them are added to firebase

    String image = "this will be an image";                         // place holder until images are supported
    String description = " this will be a course descripton for " + name;
    String category = "this will be the course category for " + name;


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
}
