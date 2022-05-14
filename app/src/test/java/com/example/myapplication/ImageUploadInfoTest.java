package com.example.myapplication;

import static org.junit.Assert.*;

import org.junit.Test;

public class ImageUploadInfoTest {

    @Test
    public void getImageName() {
        ImageUploadInfo imgUpload = new ImageUploadInfo("ImageName", "");
        assertEquals("ImageName", imgUpload.getImageName());
    }

    @Test
    public void getImageURL() {
        ImageUploadInfo imgUpload = new ImageUploadInfo("", "ImageURL");
        assertEquals("ImageURL", imgUpload.getImageURL());
    }

//    @Test
//    public void setImageURL() {
//        ImageUploadInfo imgUpload = new ImageUploadInfo("", "ImageURL");
//        imgUpload.setImageURL("ImageURL");
//        assertEquals("ImageURL", imgUpload.getImageURL());
//    }
}