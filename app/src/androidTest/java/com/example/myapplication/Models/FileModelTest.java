package com.example.myapplication.Models;

import static org.junit.Assert.assertEquals;

import com.example.myapplication.filemodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FileModelTest {
    // Creating a hypothetical user for tests
    filemodel fileModel = new filemodel("testTitle", "testVUrl", "testSubject", "testTopic");

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetters() {
        String expected = "testTitle";
        assertEquals(expected, fileModel.getTitle());

        String expected1 = "testVUrl";
        assertEquals(expected1, fileModel.getVurl());

        String expected2 = "testSubject";
        assertEquals(expected2, fileModel.getSubject_name());

        String expected3 = "testTopic";
        assertEquals(expected3, fileModel.getTopic_name());
    }

    @Test
    public void setTitle() {
        fileModel.setTitle("bored");
        String expected = "bored";
        assertEquals(expected, fileModel.getTitle());
    }

    @Test
    public void setVUrl() {
        fileModel.setVurl("url");
        String expected = "url";
        assertEquals(expected, fileModel.getVurl());
    }

    @Test
    public void setSubject() {
        fileModel.setSubject_name("pain");
        String expected = "pain";
        assertEquals(expected, fileModel.getSubject_name());
    }

    @Test
    public void setTopic() {
        fileModel.setTopic_name("kmn");
        String expected = "kmn";
        assertEquals(expected, fileModel.getTopic_name());
    }
}
