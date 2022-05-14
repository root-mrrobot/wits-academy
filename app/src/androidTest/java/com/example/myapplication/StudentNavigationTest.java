package com.example.myapplication;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import android.app.Activity;
import android.app.Instrumentation;
import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class StudentNavigationTest {

    @Rule
    public ActivityTestRule<StudentNavigation> navActivityTestRule = new ActivityTestRule<>(StudentNavigation.class);

    private StudentNavigation studentNavigate = null;

    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(StudentHomeFragment.class.getName(),null ,false);
    Instrumentation.ActivityMonitor monitor2 = getInstrumentation().addMonitor(StudentSubscriptionsFragment.class.getName(),null ,false);
    Instrumentation.ActivityMonitor monitor3 = getInstrumentation().addMonitor(StudentAccountFragment.class.getName(),null ,false);

    @Before
    public void setUp() throws Exception {
        studentNavigate = navActivityTestRule.getActivity();
    }

    @Test
    public void isActivityInView(){
        onView(withId(R.id.studentNavigation)).check(matches(isDisplayed()));
    }

    @Test
    public void studentHomeTest(){
        onView(withId(R.id.home)).perform(click());
    }

    @Test
    public void studentHomeTest2(){
        View view = studentNavigate.findViewById(R.id.home);
        assertNotNull(view);
    }

    @Test
    public void studentHomeTest3(){
        onView(withId(R.id.home)).perform(click());
        Activity studentHome = getInstrumentation().waitForMonitorWithTimeout(monitor,5000);
        assertNull(studentHome);

        studentNavigate.finish();
    }

    @Test
    public void studentSubTest(){
        onView(withId(R.id.subscriptions)).perform(click());
    }

    @Test
    public void studentSubTest2(){
        View view = studentNavigate.findViewById(R.id.subscriptions);
        assertNotNull(view);
    }

    @Test
    public void studentSubTest3(){
        onView(withId(R.id.subscriptions)).perform(click());
        Activity studentSub = getInstrumentation().waitForMonitorWithTimeout(monitor2,5000);
        assertNull(studentSub);

        studentNavigate.finish();
    }

    @Test
    public void studentAccountTest(){
        onView(withId(R.id.account)).perform(click());
    }

    @Test
    public void studentAccountTest2(){
        View view = studentNavigate.findViewById(R.id.account);
        assertNotNull(view);
    }

    @Test
    public void studentAccountTest3(){
        onView(withId(R.id.account)).perform(click());
        Activity studentAccount = getInstrumentation().waitForMonitorWithTimeout(monitor3,5000);
        assertNull(studentAccount);

        studentNavigate.finish();
    }

    @After
    public void tearDown() throws Exception {
        studentNavigate = null;
    }
}