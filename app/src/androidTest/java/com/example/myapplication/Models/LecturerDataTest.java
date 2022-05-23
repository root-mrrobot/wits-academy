package com.example.myapplication.Models;

import static org.junit.Assert.assertEquals;

import com.example.myapplication.LecturerData;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LecturerDataTest {
    // Creating a hypothetical user for tests
    LecturerData lecturerData = new LecturerData("testName", "testDesc", "testCategory", "testImgUri", "testID");

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetters() {
        String expected = "testName";
        assertEquals(expected, lecturerData.getName());

        String expected1 = "testDesc";
        assertEquals(expected1, lecturerData.getDescription());

        String expected2 = "testCategory";
        assertEquals(expected2, lecturerData.getcourseCategory());

        String expected3 = "testImgUri";
        assertEquals(expected3, lecturerData.getImageUri());

        String expected4 = "testID";
        assertEquals(expected4, lecturerData.getLecID());
    }

    @Test
    public void setDescription() {
        lecturerData.setDescription("bored");
        String expected = "bored";
        assertEquals(expected, lecturerData.getDescription());
    }

    @Test
    public void setImageUri() {
        lecturerData.setImageUri("uri");
        String expected = "uri";
        assertEquals(expected, lecturerData.getImageUri());
    }

    @Test
    public void setPassword() {
        lecturerData.setLecID("kmn1234");
        String expected = "kmn1234";
        assertEquals(expected, lecturerData.getLecID());
    }
}
