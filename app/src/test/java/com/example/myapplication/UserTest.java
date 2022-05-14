package com.example.myapplication;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserTest {

    @Test
    public void getFullName() {
        User u = new User("John Doe", "", "");
        assertEquals("John Doe", u.getFullName());
    }

    @Test
    public void setFullName() {
        User u = new User("Some Name", "", "");
        u.setFullName("John Doe");
        assertEquals("John Doe", u.getFullName());
    }

    @Test
    public void getEmail() {
        User u = new User("", "johndoe@gmail.com", "");
        assertEquals("johndoe@gmail.com", u.getEmail());
    }

    @Test
    public void setEmail() {
        User u = new User("", "someone@gmail.com", "");
        u.setEmail("johndoe@gmail.com");
        assertEquals("johndoe@gmail.com", u.getEmail());
    }

    @Test
    public void getPassword() {
        User u = new User("", "", "john123");
        assertEquals("john123", u.getPassword());
    }

    @Test
    public void setPassword() {
        User u = new User("", "", "somePassword");
        u.setPassword("john123");
        assertEquals("john123", u.getPassword());
    }
}