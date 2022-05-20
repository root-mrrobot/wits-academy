package com.example.myapplication;

public class filemodel
{
    String title;
    String vurl;

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    String subject_name;

    public String getTopic_name() {
        return topic_name;
    }

    public void setTopic_name(String topic_name) {
        this.topic_name = topic_name;
    }

    String topic_name;

    public filemodel() {
    }

    public filemodel(String title, String vurl,String subject_name,String topic_name) {
        this.title = title;
        this.vurl = vurl;
        this.subject_name = subject_name;
        this.topic_name = topic_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVurl() {
        return vurl;
    }

    public void setVurl(String vurl) {
        this.vurl = vurl;
    }
}