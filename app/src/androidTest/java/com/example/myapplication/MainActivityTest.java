package com.example.myapplication;

import static org.junit.Assert.assertNotNull;

import android.view.View;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    // Activity Test Rule for Splash Screen activity
    @Rule
    public ActivityTestRule<MainActivity> MainActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    // splashScreen variable to be used throughout for tests
    private MainActivity mainActivity = null;

    // Set up method (default)
    @Before
    public void setUp() throws Exception {
        mainActivity = MainActivityTestRule.getActivity();
    }

    // Testing to see if image does exist and is not empty
    @Test
    public void imgViewTest() {
        View view = mainActivity.findViewById(R.id.mainActivity);
        assertNotNull(view); // Making sure the view of image is not empty
    }

    // After method (default)
    @After
    public void tearDown() throws Exception {
        mainActivity = null;
    }
}