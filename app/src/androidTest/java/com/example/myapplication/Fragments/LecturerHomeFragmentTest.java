package com.example.myapplication.Fragments;

import static org.junit.Assert.*;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LecturerHomeFragmentTest {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseUser user;
    DatabaseReference ref;

    @Before
    public void setUp() throws Exception {
        database.useEmulator("10.0.0.2.2", 9000);
    }

    @Test
    public void testGetUser(){
        user = FirebaseAuth.getInstance().getCurrentUser();
        ref = FirebaseDatabase.getInstance().getReference("Users");
        assertNotNull(user);
    }

    @After
    public void tearDown() throws Exception {
        database.getReference().setValue(null);
    }


}