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
public class LecturerNavigationTest {

    // Activity Test Rule for Lecturer Navigation Activity
    @Rule
    public ActivityTestRule<LecturerNavigation> LecturerNavigationTestRule = new ActivityTestRule<>(LecturerNavigation.class);

    // lecturerNavigation variable to be used throughout for tests
    private LecturerNavigation lecturerNavigation = null;

    // Set up method (default)
    @Before
    public void setUp() throws Exception {
        lecturerNavigation = LecturerNavigationTestRule.getActivity();
    }

    // Checking if the activity appears and whether is matches the id 'lecturerNavigation'
    @Test
    public void isActivityInView(){
        onView(withId(R.id.lecturerNavigation)).check(matches(isDisplayed()));
    }

    // After method (default)
    @After
    public void tearDown() throws Exception {
        lecturerNavigation = null;
    }
}