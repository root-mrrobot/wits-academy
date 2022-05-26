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

    @Test
    public void isTextHintInView(){
        onView(withHint("Click here to upload pdf")).check(matches(isDisplayed()));
    }

    @Test
    public void pdfBtnTest(){
        TextView textViewTest = lecUploadPdf.findViewById(R.id.btnExit);
        String actual = textViewTest.getText().toString();
        String expected = "Exit";

        assertEquals(actual,expected);
        lecUploadPdf.finish();
    }

    @Test
    public void videoBtnTest(){
        TextView textViewTest = lecUploadPdf.findViewById(R.id.btn_pdf);
        String actual = textViewTest.getText().toString();
        String expected = "Upload pdf";

        assertEquals(actual,expected);
        lecUploadPdf.finish();
    }

    // After method (default)
    @After
    public void tearDown() throws Exception {
        lecUploadPdf = null;
    }
}