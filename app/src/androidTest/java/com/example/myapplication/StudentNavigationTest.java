package com.example.myapplication;

import static org.junit.Assert.*;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class StudentNavigationTest {

    @Rule
    public ActivityTestRule<StudentNavigation> studentNavigationActivityRule = new ActivityTestRule<>(StudentNavigation.class);

    @Before
    public void init(){
        studentNavigationActivityRule.getActivity().getSupportFragmentManager().beginTransaction();
    }

    @Test
    public void testLaunch(){
        assertNotNull(studentNavigationActivityRule.getActivity().getSupportFragmentManager().beginTransaction());
    }

    @After
    public void finish(){
        studentNavigationActivityRule.getActivity().finish();
    }

}