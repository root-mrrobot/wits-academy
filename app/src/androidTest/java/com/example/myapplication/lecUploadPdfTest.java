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
public class lecUploadPdfTest {

    // Activity Test Rule for Lec Upload Pdf Activity
    @Rule
    public ActivityTestRule<lecUploadPdf> lecUploadPdfTestRule = new ActivityTestRule<>(lecUploadPdf.class);

    // lecUploadPdf variable to be used throughout for tests
    private lecUploadPdf lecUploadPdf = null;

    // Set up method (default)
    @Before
    public void setUp() throws Exception {
        lecUploadPdf = lecUploadPdfTestRule.getActivity();
    }

    // Checking if the activity appears and whether is matches the id 'lecUploadPdf'
    @Test
    public void isActivityInView(){
        onView(withId(R.id.lecUploadPdf)).check(matches(isDisplayed()));
    }

    // After method (default)
    @After
    public void tearDown() throws Exception {
        lecUploadPdf = null;
    }
}