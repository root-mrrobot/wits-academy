package com.example.myapplication.Fragments;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

import android.app.Instrumentation;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.android21buttons.fragmenttestrule.FragmentTestRule;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.StudentHomeFragment;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class StudentHomeFragmentTest {
    @Rule
    public FragmentTestRule<?, StudentHomeFragment> studentHomeFragmentTestRule = FragmentTestRule.create(StudentHomeFragment.class);
    StudentHomeFragment studentHomeFragment = studentHomeFragmentTestRule.getFragment();

    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(MainActivity.class.getName(),null ,false);

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void isActivityInView(){
        onView(withId(R.id.studentHomeFragment)).check(matches(isDisplayed()));
    }

    @After
    public void tearDown() throws Exception {
    }
}
