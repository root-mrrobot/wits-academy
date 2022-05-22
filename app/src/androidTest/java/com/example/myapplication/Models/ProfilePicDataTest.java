package com.example.myapplication.Models;

import static org.junit.Assert.assertEquals;

import com.example.myapplication.profilePic_data;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ProfilePicDataTest {
    // Creating a hypothetical model for tests
    profilePic_data profileData = new profilePic_data("testProfileUri", "testEmail");

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetters() {
        String expected = "testProfileUri";
        assertEquals(expected, profileData.getProfilePic_URI());

        String expected1 = "testEmail";
        assertEquals(expected1, profileData.getEmail_());
    }

    @Test
    public void setProfilePic_URI() {
        profileData.setProfilePic_URI("bored");
        String expected = "bored";
        assertEquals(expected, profileData.getProfilePic_URI());
    }

    @Test
    public void setEmail() {
        profileData.setEmail_("gmail");
        String expected = "gmail";
        assertEquals(expected, profileData.getEmail_());
    }
}
