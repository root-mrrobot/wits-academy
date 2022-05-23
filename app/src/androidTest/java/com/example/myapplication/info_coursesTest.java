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
public class info_coursesTest {

    @Rule
    public ActivityTestRule<info_courses> infoCourseActivityRule = new ActivityTestRule<>(info_courses.class);

    private info_courses infoCourse = null;

    @Before
    public void setUp() throws Exception {
        infoCourse = infoCourseActivityRule.getActivity();
    }

    @Test
    public void testLaunch() {
        assertNotNull(infoCourse.getWindow());
    }



    @After
    public void tearDown() throws Exception {
        infoCourse = null;
    }



}