package com.example.myapplication;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

import android.app.Instrumentation;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class StudentNavigationTest {

    // Activity Test Rule for Splash Screen activity
    @Rule
    public ActivityTestRule<StudentNavigation> StudentNavigationTestRule = new ActivityTestRule<>(StudentNavigation.class);

    // splashScreen variable to be used throughout for tests
    private StudentNavigation studentNavigation = null;

    // Monitor to be used for Instrumentation
    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(MainActivity.class.getName(),null ,false);

    // Set up method (default)
    @Before
    public void setUp() throws Exception {
    }

//    @Test
//    public void isActivityInView(){
//        onView(withId(R.id.studentNavigation)).check(matches(isDisplayed()));
//    }

    @Test
    public void isBottomNavInView(){
        onView(withId(R.id.bottomNavigationView)).check(matches(isDisplayed()));
    }

//    @Test
//    public void isHomeInView(){
//        onView(withId(R.id.home)).check(matches(isDisplayed()));
//    }

//    @Test
//    public void isAccountInView(){
//        onView(withId(R.id.account)).check(matches(isDisplayed()));
//    }

    @Test
    public void isSubscriptionsInView(){
        onView(withId(R.id.subscriptions)).check(matches(isDisplayed()));
    }

    // After method (default)
    @After
    public void tearDown() throws Exception {
    }
}