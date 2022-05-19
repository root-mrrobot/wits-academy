package com.example.myapplication;

import static org.junit.Assert.*;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.junit.Test;

public class LecturerCourseInfoChecksTest {

    //TODO: Mock user and activity so that it can be tested

/*
    @Test
    public void noUser() {

        boolean output = LecturerCourseInfoChecks.noUser(FirebaseAuth.getInstance().getCurrentUser());
        assertFalse(output);

    }

    @Test
    public void user(){

        boolean output = LecturerCourseInfoChecks.noUser(FirebaseAuth.getInstance().getCurrentUser());
        assertTrue(output);

    }


    @Test
    public void successfulActivityResult() {
    }

    @Test
    public void unsuccessfulActivityResult(){

    }
    */

    @Test
    public void emptyCourseName() {
        boolean output = LecturerCourseInfoChecks.emptyString("");
        assertTrue(output);
    }

    @Test
    public void emptyCourseDescription(){
        boolean output = LecturerCourseInfoChecks.emptyString("");
        assertTrue(output);

    }

    @Test
    public void notEmptyCourseName(){
        boolean output = LecturerCourseInfoChecks.emptyString("Software Design");
        assertFalse(output);
    }

    @Test
    public void notEmptyCourseDescription(){
        boolean output = LecturerCourseInfoChecks.emptyString("Learn about the process by which an agent creates a specification of a software artifact intended to accomplish goals, using a set of primitive components and subject to constraints.");
        assertFalse(output);
    }

    /*
    @Test
    public void noImage() {
        boolean output = LecturerCourseInfoChecks.noImage();
        assertTrue(output);
    }

    @Test
    public void image(){
        boolean output = LecturerCourseInfoChecks.noImage();
        assertFalse(output);
    }
    */
}