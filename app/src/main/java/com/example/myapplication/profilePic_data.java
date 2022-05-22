package com.example.myapplication;

public class profilePic_data {


    String profilePic_URI;



    String email_;

    public profilePic_data(String profilePic_URI,String email_) {
        this.profilePic_URI = profilePic_URI;
        this.email_ = email_;
    }

    public String getProfilePic_URI() {
        return profilePic_URI;
    }

    public void setProfilePic_URI(String profilePic_URI) {
        this.profilePic_URI = profilePic_URI;
    }
    public String getEmail_() {
        return email_;
    }

    public void setEmail_(String email_) {
        this.email_ = email_;
    }



}
