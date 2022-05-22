package com.example.myapplication;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
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
public class StudentNavigationTest {

    // Activity Test Rule for Student Navigation Activity
    @Rule
    public ActivityTestRule<StudentNavigation> StudentNavigationTestRule = new ActivityTestRule<>(StudentNavigation.class);

    // studentNavigation variable to be used throughout for tests
    private StudentNavigation studentNavigation = null;

    // Set up method (default)
    @Before
    public void setUp() throws Exception {
        studentNavigation = StudentNavigationTestRule.getActivity();
    }

    // Checking if the activity appears and whether is matches the id 'studentNavigation'
    @Test
    public void isActivityInView(){
        onView(withId(R.id.studentNavigation)).check(matches(isDisplayed()));
    }

//    @Test
//    public void SubNavTest() {
//        onView(withId(R.id.subscriptions)).perform(click());
//
//        onView(withId(R.id.studentSubscriptionsFragment)).check(matches(isDisplayed()));
//
//        pressBack();
//    }

    // After method (default)
    @After
    public void tearDown() throws Exception {
        studentNavigation = null;
    }
}