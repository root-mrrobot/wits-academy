package com.example.myapplication;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertNull;

import android.app.Activity;
import android.app.Instrumentation;

import androidx.test.core.app.ActivityScenario;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

public class StudentNavigationTest {
    @Rule
    public ActivityTestRule<StudentNavigation> studentNavigationActivityTestRule = new ActivityTestRule<StudentNavigation>(StudentNavigation.class);

    private StudentNavigation navigate = null;

    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(MainActivity.class.getName(),null ,false);

    @Test
    public void testStudentNavigation() {

        //SETUP
        try (ActivityScenario<StudentNavigation> scenario = ActivityScenario.launch(StudentNavigation.class)) {

            //Nav Subscriptions
            onView(withId(R.id.subscriptions)).perform(click());

            //TEST
            Activity subscriptions = getInstrumentation().waitForMonitorWithTimeout(monitor,5000);
            assertNull(subscriptions);

            //Nav Account
            onView(withId(R.id.account)).perform(click());

            //TEST
            Activity account = getInstrumentation().waitForMonitorWithTimeout(monitor,5000);
            assertNull(account);

            //Nav Home
            onView(withId(R.id.home)).perform(click());

            //TEST
            Activity home = getInstrumentation().waitForMonitorWithTimeout(monitor,5000);
            assertNull(home);


        }
    }
}
