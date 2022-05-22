package com.example.myapplication.Models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.example.myapplication.ImageUploadInfo;

public class ImageUploadInfoTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

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
}