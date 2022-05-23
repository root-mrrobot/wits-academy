package com.example.myapplication;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertNotNull;

import android.app.Instrumentation;
import android.view.View;

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

    // Checking if the activity appears and whether is matches the id 'home'
    @Test
    public void isActivityInView(){
        onView(withId(R.id.lecturerResource)).check(matches(isDisplayed()));
    }

    // After method (default)
    @After
    public void tearDown() throws Exception {
        lecturerResource = null;
    }
}