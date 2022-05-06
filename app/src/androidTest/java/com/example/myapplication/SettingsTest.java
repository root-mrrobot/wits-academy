package com.example.myapplication;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import android.app.Activity;
import android.app.Instrumentation;
import android.view.View;
import android.widget.TextView;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class SettingsTest {

    @Rule
    public ActivityTestRule<Settings> loginActivityTestRule = new ActivityTestRule<Settings>(Settings.class);

    private Settings settings = null;

    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(MainActivity.class.getName(),null ,false);

    public static final String STRING_TO_BE_TYPED_NEW_FULL_NAME = "Jane Doe";
    public static final String STRING_TO_BE_TYPED_NEW_EMAIL = "janedoe@gmail.com";
    public static final String STRING_TO_BE_TYPED_NEW_PASSWORD = "janed123";
    public static final String STRING_TO_BE_TYPED_NEW_CONFIRM_PASSWORD = "janed123";

    @Before
    public void setUp() throws Exception {
        settings = loginActivityTestRule.getActivity();
    }

    @After
    public void tearDown() throws Exception {
        settings = null;
    }
}