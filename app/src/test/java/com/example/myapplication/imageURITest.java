package com.example.myapplication;

import static org.junit.Assert.*;

import org.junit.Test;

public class imageURITest {

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