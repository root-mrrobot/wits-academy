package com.example.myapplication.Models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.example.myapplication.imageURI;

public class ImageURITest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getImageUri() {
        imageURI imgURI = new imageURI("some string");
        assertEquals("some string", imgURI.getImageUri());
    }

    @Test
    public void setImageUri() {
        imageURI imgURI = new imageURI("some string");
        imgURI.setImageUri("some string");
        assertEquals("some string", imgURI.getImageUri());
    }
}