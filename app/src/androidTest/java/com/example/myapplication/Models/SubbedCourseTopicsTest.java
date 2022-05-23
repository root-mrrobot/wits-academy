package com.example.myapplication.Models;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.myapplication.R;
import com.example.myapplication.SubbedCourseTopics;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class SubbedCourseTopicsTest {

    @Rule
    public ActivityTestRule<SubbedCourseTopics> DisplaySubbedTopicsTestRule = new ActivityTestRule<>(SubbedCourseTopics.class);

    private SubbedCourseTopics displaySubbedTopics = null;

    @Before
    public void setUp() throws Exception {
        displaySubbedTopics = DisplaySubbedTopicsTestRule.getActivity();
    }

    @Test
    public void isActivityInView(){
        onView(withId(R.id.displaySubbedTopics)).check(matches(isDisplayed()));
    }

    @After
    public void tearDown() throws Exception {
        displaySubbedTopics = null;
    }

}