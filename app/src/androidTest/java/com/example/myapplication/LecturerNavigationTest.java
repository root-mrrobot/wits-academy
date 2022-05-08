package com.example.myapplication;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertNull;

import android.app.Activity;
import android.app.Instrumentation;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class LecturerNavigationTest {

    @Rule
    public ActivityTestRule<LecturerNavigation> navActivityTestRule = new ActivityTestRule<LecturerNavigation>(LecturerNavigation.class);

    private LecturerNavigation navigate = null;

    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(Home.class.getName(),null ,false);

   @Test
    public void testLecturerNavigation() {

        //SETUP
        try (ActivityScenario<LecturerNavigation> scenario = ActivityScenario.launch(LecturerNavigation.class)) {

            //Nav Courses
            onView(withId(R.id.courses)).perform(click());

            //TEST
            Activity courses = getInstrumentation().waitForMonitorWithTimeout(monitor,5000);
            assertNull(courses);

            //Nav Account
            onView(withId(R.id.account)).perform(click());

            //TEST
            Activity account = getInstrumentation().waitForMonitorWithTimeout(monitor,5000);
            assertNull(account);

            //Nav Home
            onView(withId(R.id.lecturerHome)).perform(click());

            //TEST
            Activity home = getInstrumentation().waitForMonitorWithTimeout(monitor,5000);
            assertNull(home);


        }
    }
}
