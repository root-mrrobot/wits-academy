package com.example.myapplication.Models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.example.myapplication.User;

public class UserTest {
    User user = new User("John Doe", "john@gmail.com", "john123");

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetters() {
        String expected = "John Doe";
        assertEquals(expected, user.getFullName());

        String expected1 = "john123";
        assertEquals(expected1, user.getPassword());

        String expected3 = "john@gmail.com";
        assertEquals(expected3, user.getEmail());
    }

    @Test
    public void setUsername() {
        user.setFullName("bored");
        String expected = "bored";
        assertEquals(expected, user.getFullName());
    }

    @Test
    public void setEmail() {
        user.setEmail("gmail");
        String expected = "gmail";
        assertEquals(expected, user.getEmail());
    }

    @Test
    public void setPassword() {
        user.setPassword("kmn1234");
        String expected = "kmn1234";
        assertEquals(expected, user.getPassword());
    }
}