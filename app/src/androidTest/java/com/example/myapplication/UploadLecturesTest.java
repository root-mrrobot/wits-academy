package com.example.myapplication;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static org.junit.Assert.assertEquals;

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

    @Test
    public void isTextHintInView(){
        onView(withHint("Add Video heading")).check(matches(isDisplayed()));
    }

    @Test
    public void browseBtnTest(){
        TextView textViewTest = uploadLectures.findViewById(R.id.btn_vidBrowse);
        String actual = textViewTest.getText().toString();
        String expected = "Browse";

        assertEquals(actual,expected);
        uploadLectures.finish();
    }

    @Test
    public void uploadBtnTest(){
        TextView textViewTest = uploadLectures.findViewById(R.id.btn_vidUpload);
        String actual = textViewTest.getText().toString();
        String expected = "Upload";

        assertEquals(actual,expected);
        uploadLectures.finish();
    }

    @Test
    public void exitBtnTest(){
        TextView textViewTest = uploadLectures.findViewById(R.id.btnExit);
        String actual = textViewTest.getText().toString();
        String expected = "Exit";

        assertEquals(actual,expected);
        uploadLectures.finish();
    }

    // After method (default)
    @After
    public void tearDown() throws Exception {
        uploadLectures = null;
    }
}