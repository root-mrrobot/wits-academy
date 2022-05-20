package com.example.myapplication;

public class Pdf_data {
    public String url_pdf;
    public String name_pdf;
    public String subject_name,topic_name;
    public Pdf_data() {
    }

    public Pdf_data(String name_pdf,String url_pdf,String subject_name, String topic_name) {
        this.name_pdf = name_pdf;
        this.url_pdf = url_pdf;
        this.subject_name=subject_name;
        this.topic_name = topic_name;
    }

    public String getName_pdf() {
        return name_pdf;
    }

    public void setName_pdf(String name_pdf) {
        this.name_pdf = name_pdf;
    }



    public String getUrl_pdf() {
        return url_pdf;
    }

    public void setUrl_pdf(String url_pdf) {
        this.url_pdf = url_pdf;
    }


}
