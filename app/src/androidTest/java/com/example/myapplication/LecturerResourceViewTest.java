package com.example.myapplication;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
public class LecturerResourceViewTest {

    // Activity Test Rule for Splash Screen activity
    @Rule
    public ActivityTestRule<LecturerResourceView> LecturerResourceViewActivityTestRule = new ActivityTestRule<>(LecturerResourceView.class);

    // splashScreen variable to be used throughout for tests
    private LecturerResourceView lecturerResource = null;

    // Monitor to be used for Instrumentation
    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(MainActivity.class.getName(),null ,false);

    // Set up method (default)
    @Before
    public void setUp() throws Exception {
        lecturerResource = LecturerResourceViewActivityTestRule.getActivity();
    }

    // Checking if the activity appears and whether is matches the id 'lecturerResource'
    @Test
    public void isActivityInView(){
        onView(withId(R.id.lecturerResource)).check(matches(isDisplayed()));
    }

    @Test
    public void pdfTextTest() {
        View view = lecturerResource.findViewById(R.id.pdfResources);
        assertNotNull(view);
    }

    @Test
    public void pdfTextTest2(){
        TextView textViewTest = lecturerResource.findViewById(R.id.pdfResources);
        String actual = textViewTest.getText().toString();
        String expected = "PDF RESOURCES";

        assertEquals(actual,expected);
        lecturerResource.finish();
    }

    @Test
    public void videoTextTest() {
        View view = lecturerResource.findViewById(R.id.videoResources);
        assertNotNull(view);
    }

    @Test
    public void videoTextTest2(){
        TextView textViewTest = lecturerResource.findViewById(R.id.videoResources);
        String actual = textViewTest.getText().toString();
        String expected = "VIDEO RESOURCES";

        assertEquals(actual,expected);
        lecturerResource.finish();
    }

    @Test
    public void pdfBtnTest(){
        TextView textViewTest = lecturerResource.findViewById(R.id.btnUploadPDF);
        String actual = textViewTest.getText().toString();
        String expected = "UPLOAD A PDF";

        assertEquals(actual,expected);
        lecturerResource.finish();
    }

    @Test
    public void videoBtnTest(){
        TextView textViewTest = lecturerResource.findViewById(R.id.btnUploadVid);
        String actual = textViewTest.getText().toString();
        String expected = "UPLOAD A VIDEO";

        assertEquals(actual,expected);
        lecturerResource.finish();
    }

    @Test
    public void quizBtnTest(){
        TextView textViewTest = lecturerResource.findViewById(R.id.btn_addQuiz);
        String actual = textViewTest.getText().toString();
        String expected = "Create a Quiz";

        assertEquals(actual,expected);
        lecturerResource.finish();
    }

    // After method (default)
    @After
    public void tearDown() throws Exception {
        lecturerResource = null;
    }
}