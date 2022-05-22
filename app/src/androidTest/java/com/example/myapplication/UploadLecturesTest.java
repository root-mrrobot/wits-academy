package com.example.myapplication;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import android.app.Activity;
import android.app.Instrumentation;
import android.view.View;
import android.widget.TextView;

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

    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(MainActivity.class.getName(),null ,false);

    // uploadLectures variable to be used throughout for tests
    private UploadLectures uploadLectures = null;

    // Set up method (default)
    @Before
    public void setUp() throws Exception {
        uploadLectures = UploadLecturesTestRule.getActivity();
    }

    // Checking if the activity appears and whether is matches the id 'uploadLectures'
//    @Test
//    public void isActivityInView(){
//        onView(withId(R.id.uploadLectures)).check(matches(isDisplayed()));
//    }

    @Test
    public void uploadPdfTextTest(){
        TextView textViewTest = uploadLectures.findViewById(R.id.pdf_intent);
        String actual = textViewTest.getText().toString();
        String expected = "Click here if you want to upload pdfs";

        assertEquals(actual,expected);
        uploadLectures.finish();
    }

//    @Test
//    public void uploadPdfTextTest2() {
//        View view = uploadLectures.findViewById(R.id.pdf_intent);
//        assertNotNull(view);
//    }

//    @Test
//    public void testVideoBrowseButton(){
//        onView(withId(R.id.btn_vidBrowse)).perform(click());
//        Activity browse = getInstrumentation().waitForMonitorWithTimeout(monitor,5000);
//        assertNull(browse);
//
//        uploadLectures.finish();
//    }

    // After method (default)
    @After
    public void tearDown() throws Exception {
        uploadLectures = null;
    }
}