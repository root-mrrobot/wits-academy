package com.example.myapplication;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import android.app.Instrumentation;
import android.view.View;
import android.widget.TextView;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class SplashScreenTest {

    // Activity Test Rule for Splash Screen activity
    @Rule
    public ActivityTestRule<SplashScreen> splashScreenActivityTestRule = new ActivityTestRule<SplashScreen>(SplashScreen.class);

    // splashScreen variable to be used throughout for tests
    private SplashScreen splashScreen = null;

    // Monitor to be used for Instrumentation
    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(MainActivity.class.getName(),null ,false);

    // Set up method (default)
    @Before
    public void setUp() throws Exception {
        splashScreen = splashScreenActivityTestRule.getActivity();
    }

    // Testing to see if image does exist and is not empty
    @Test
    public void imgViewTest() {
        View view = splashScreen.findViewById(R.id.WitsAcademyimg);
        assertNotNull(view); // Making sure the view of image is not empty
    }

    // Testing to see if developed by text does exist and is not empty
    @Test
    public void devTextTest() {
        View view = splashScreen.findViewById(R.id.slogan1);
        assertNotNull(view); // Making sure the view of text is not empty
    }

    // Testing to see if teamName text does exist and is not empty
    @Test
    public void teamNameTextTest() {
        View view = splashScreen.findViewById(R.id.slogan2);
        assertNotNull(view); // Making sure the view of text is not empty
    }

    // Testing to see if all items are launched and not empty
    @Test
    public void testLaunchItems(){
        // Assigning view variables to different components from the UI
        View img = splashScreen.findViewById(R.id.WitsAcademyimg);
        View dev = splashScreen.findViewById(R.id.slogan1);
        View teamName = splashScreen.findViewById(R.id.slogan2);

        // Making sure the view is not empty
        assertNotNull(img);
        assertNotNull(dev);
        assertNotNull(teamName);

        splashScreen.finish();
    }

    // After method (default)
    @After
    public void tearDown() throws Exception {
        splashScreen = null;
    }
}