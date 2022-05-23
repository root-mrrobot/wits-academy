package com.example.myapplication.Fragments;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

import android.app.Instrumentation;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.android21buttons.fragmenttestrule.FragmentTestRule;
import com.example.myapplication.LecturerHomeFragment;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class LecturerHomeFragmentTest {

    // Fragment Test Rule for Lecturer Account Fragment
    @Rule
    public FragmentTestRule<?, LecturerHomeFragment> lecturerHomeFragmentTestRule = FragmentTestRule.create(LecturerHomeFragment.class);
    //LecturerHomeFragment lecturerHomeFragment = lecturerHomeFragmentTestRule.getFragment();

    // Monitor to be used for Instrumentation
    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(MainActivity.class.getName(),null ,false);

    @Before
    public void setUp() throws Exception {
    }

    // Checking if the activity appears and whether is matches the id 'lecturerHomeFragment'
    @Test
    public void isActivityInView(){
        onView(withId(R.id.lecturerHomeFragment)).check(matches(isDisplayed()));
    }

    // Testing to see if the Spinner is displayed
//    @Test
//    public void spinnerTest() {
//        onView(withId(R.id.spinner)).check(matches(isDisplayed()));
//    }

    // Testing to see if the Spinner is clickable
//    @Test
//    public void spinnerTest2() {
//        onView(withId(R.id.spinner)).check(matches(isClickable()));
//    }

    // Testing to see if the Course Name is displayed
    @Test
    public void courseTextViewTest() {
        onView(withId(R.id.course_name)).check(matches(isDisplayed()));
    }

    // Testing to see if the Course Name is clickable
//    @Test
//    public void courseTextViewTest2() {
//        onView(withId(R.id.course_name)).check(matches(isClickable()));
//    }

    // Testing to see if the Course Description is displayed
    @Test
    public void textViewTest() {
        onView(withId(R.id.course_rating)).check(matches(isDisplayed()));
    }

    // Testing to see if the Course Description is clickable
//    @Test
//    public void textViewTest2() {
//        onView(withId(R.id.course_rating)).check(matches(isClickable()));
//    }

    // Testing to see if the Image is displayed
    @Test
    public void imgViewTest() {
        onView(withId(R.id.imgView)).check(matches(isDisplayed()));
    }

    // Testing to see if the Upload Button is displayed
    @Test
    public void uploadBtnTest() {
        onView(withId(R.id.btnUpload)).check(matches(isDisplayed()));
    }

    // Testing to see if the Upload Button is clickable
//    @Test
//    public void uploadBtnTest2() {
//        onView(withId(R.id.btnUpload)).check(matches(isClickable()));
//    }

    // Testing to see if the Choose Button is displayed
    @Test
    public void chooseBtnTest() {
        onView(withId(R.id.btnChoose)).check(matches(isDisplayed()));
    }

    // Testing to see if the Choose Button is clickable
//    @Test
//    public void chooseBtnTest2() {
//        onView(withId(R.id.btnChoose)).check(matches(isClickable()));
//    }

    @After
    public void tearDown() throws Exception {
    }
}
