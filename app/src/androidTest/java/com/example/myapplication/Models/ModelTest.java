package com.example.myapplication.Models;

import static org.junit.Assert.assertEquals;

import com.example.myapplication.Model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ModelTest {
    // Creating a hypothetical model for tests
    Model model = new Model("testUri", "test");

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetters() {
        String expected = "testUri";
        assertEquals(expected, model.getImage_Uri());

        String expected1 = "test";
        assertEquals(expected1, model.getName());
    }

    @Test
    public void setImage_Uri() {
        model.setImage_Uri("bored");
        String expected = "bored";
        assertEquals(expected, model.getImage_Uri());
    }

    @Test
    public void setName() {
        model.setName("gmail");
        String expected = "gmail";
        assertEquals(expected, model.getName());
    }

}
