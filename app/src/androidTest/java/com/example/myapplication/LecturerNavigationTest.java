package com.example.myapplication;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

import android.app.Activity;
import android.app.Instrumentation;
import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class LecturerNavigationTest {

    @Rule
    public ActivityTestRule<LecturerNavigation> navActivityTestRule = new ActivityTestRule<>(LecturerNavigation.class);

    private LecturerNavigation lecturerNavigate = null;

    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(LecturerHomeFragment.class.getName(),null ,false);
    Instrumentation.ActivityMonitor monitor2 = getInstrumentation().addMonitor(LecturerCoursesFragment.class.getName(),null ,false);
    Instrumentation.ActivityMonitor monitor3 = getInstrumentation().addMonitor(LecturerAccountFragment.class.getName(),null ,false);

    @Before
    public void setUp() throws Exception {
        lecturerNavigate = navActivityTestRule.getActivity();
    }

//    @Test
//    public void isActivityInView(){
//        onView(withId(R.id.lecturerNavigation)).check(matches(isDisplayed()));
//    }

    @Test
    public void lecturerHomeTest(){
        onView(withId(R.id.lecturerHome)).perform(click());
    }

    @Test
    public void lecturerHomeTest2(){
        View view = lecturerNavigate.findViewById(R.id.lecturerHome);
        assertNotNull(view);
    }

    @Test
    public void lecturerHomeTest3(){
        onView(withId(R.id.lecturerHome)).perform(click());
        Activity lecturerHome = getInstrumentation().waitForMonitorWithTimeout(monitor,5000);
        assertNull(lecturerHome);

        lecturerNavigate.finish();
    }

    @Test
    public void lecturerCoursesTest(){
        onView(withId(R.id.courses)).perform(click());
    }

    @Test
    public void lecturerCoursesTest2(){
        View view = lecturerNavigate.findViewById(R.id.courses);
        assertNotNull(view);
    }

    @Test
    public void lecturerCoursesTest3(){
        onView(withId(R.id.courses)).perform(click());
        Activity lecturerCourses = getInstrumentation().waitForMonitorWithTimeout(monitor2,5000);
        assertNull(lecturerCourses);

        lecturerNavigate.finish();
    }

    @Test
    public void lecturerAccountTest(){
        onView(withId(R.id.account)).perform(click());
    }

    @Test
    public void lecturerAccountTest2(){
        View view = lecturerNavigate.findViewById(R.id.account);
        assertNotNull(view);
    }

    @Test
    public void lecturerAccountTest3(){
        onView(withId(R.id.account)).perform(click());
        Activity lecturerAccount = getInstrumentation().waitForMonitorWithTimeout(monitor3,5000);
        assertNull(lecturerAccount);

        lecturerNavigate.finish();
    }

    @After
    public void tearDown() throws Exception {
        lecturerNavigate = null;
    }
}