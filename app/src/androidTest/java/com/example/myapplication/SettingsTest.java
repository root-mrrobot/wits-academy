package com.example.myapplication;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
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

    @Test
    public void isActivityInView(){
        onView(withId(R.id.settings)).check(matches(isDisplayed()));
    }

    @Test
    public void settingsTextTest() {
        View view = settings.findViewById(R.id.editProfileText);
        assertNotNull(view);
    }

    @Test
    public void settingsTextTest2(){
        TextView textViewTest = settings.findViewById(R.id.editProfileText);
        String actual = textViewTest.getText().toString();
        String expected = "Edit Profile";

        assertEquals(actual,expected);
        settings.finish();
    }

//    @Test
//    public void NameTextTest(){
//        TextView textViewTest = settings.findViewById(R.id.settingsNameTextView);
//        String actual = textViewTest.getText().toString();
//        String expected = "Name:";
//
//        assertEquals(actual,expected);
//        settings.finish();
//    }
//
//    @Test
//    public void EmailTextTest(){
//        TextView textViewTest = settings.findViewById(R.id.settingsEmailTextView);
//        String actual = textViewTest.getText().toString();
//        String expected = "Email:";
//
//        assertEquals(actual,expected);
//        settings.finish();
//    }

    @Test
    public void ImageTest() {
        View view = settings.findViewById(R.id.editProfilePic);
        assertNotNull(view);
    }

    @Test
    public void settingsNameTextTest() {
        View view = settings.findViewById(R.id.settingsName);
        assertNotNull(view);
    }

    @Test
    public void testLaunchItems(){
        View editProfileText = settings.findViewById(R.id.editProfileText);
        View name = settings.findViewById(R.id.settingsName);
//        View editName = settings.findViewById(R.id.settingsNameEditText);
//        View editEmail = settings.findViewById(R.id.settingsEmailEditText);
//        View btnUpdate = settings.findViewById(R.id.btnUpdate);
        View btnChangePw = settings.findViewById(R.id.btnChangePw);

        assertNotNull(editProfileText);
        assertNotNull(name);
//        assertNotNull(editName);
//        assertNotNull(editEmail);
//        assertNotNull(btnUpdate);
        assertNotNull(btnChangePw);

        settings.finish();
    }

//    @Test
//    public void testChangePwBtn(){
//        onView(withId(R.id.btnChangePw)).perform(click());
//    }
//
//    @Test
//    public void testChangePwBtn2(){
//        View view = settings.findViewById(R.id.btnChangePw);
//        assertNotNull(view);
//    }
//
//    @Test
//    public void testChangePwBtn3(){
//        onView(withId(R.id.btnChangePw)).perform(click());
//        Activity settings = getInstrumentation().waitForMonitorWithTimeout(monitor,5000);
//        assertNull(settings);
//
//        //settings.finish();
//    }

//    @Test
//    public void testChangePwBtn4(){
//        TextView textViewTest = settings.findViewById(R.id.btnChangePw);
//        String actual = textViewTest.getText().toString();
//        String expected = "Change Password";
//
//        assertEquals(actual,expected);
//        //settings.finish();
//    }

//    @Test
//    public void testUpdateBtn(){
//        onView(withId(R.id.btnUpdate)).perform(click());
//    }
//
//    @Test
//    public void testUpdateBtn2() {
//        View view = settings.findViewById(R.id.btnUpdate);
//        assertNotNull(view);
//    }
//
//    @Test
//    public void testUpdateBtn3(){
//        onView(withId(R.id.btnUpdate)).perform(click());
//        Activity settings = getInstrumentation().waitForMonitorWithTimeout(monitor,5000);
//        assertNull(settings);
//
//        //settings.finish();
//    }

    @Test
    public void testUpdateBtn4(){
        TextView textViewTest = settings.findViewById(R.id.btnChangePw);
        String actual = textViewTest.getText().toString();
        String expected = "Update";

        assertEquals(actual,expected);
        settings.finish();
    }

    @After
    public void tearDown() throws Exception {
        settings = null;
    }
}
