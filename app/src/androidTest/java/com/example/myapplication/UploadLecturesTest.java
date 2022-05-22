package com.example.myapplication;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class UploadLecturesTest {

    // Activity Test Rule for Upload Lectures Activity
    @Rule
    public ActivityTestRule<UploadLectures> UploadLecturesTestRule = new ActivityTestRule<>(UploadLectures.class);

    // uploadLectures variable to be used throughout for tests
    private UploadLectures uploadLectures = null;

    // Set up method (default)
    @Before
    public void setUp() throws Exception {
        uploadLectures = UploadLecturesTestRule.getActivity();
    }

    // Checking if the activity appears and whether is matches the id 'uploadLectures'
    @Test
    public void isActivityInView(){
        onView(withId(R.id.uploadLectures)).check(matches(isDisplayed()));
    }

    // After method (default)
    @After
    public void tearDown() throws Exception {
        uploadLectures = null;
    }
}