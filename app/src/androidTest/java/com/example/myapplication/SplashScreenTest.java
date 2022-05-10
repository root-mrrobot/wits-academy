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

    @Rule
    public ActivityTestRule<SplashScreen> splashScreenActivityTestRule = new ActivityTestRule<SplashScreen>(SplashScreen.class);

    private SplashScreen splashScreen = null;

    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(MainActivity.class.getName(),null ,false);

    @Before
    public void setUp() throws Exception {
        splashScreen = splashScreenActivityTestRule.getActivity();
    }

    @Test
    public void imgViewTest() {
        View view = splashScreen.findViewById(R.id.WitsAcademyimg);
        assertNotNull(view);
    }

    @Test
    public void devTextTest() {
        View view = splashScreen.findViewById(R.id.slogan1);
        assertNotNull(view);
    }

//    @Test
//    public void devTextTest2(){
//        TextView textViewTest = splashScreen.findViewById(R.id.slogan1);
//        String actual = textViewTest.getText().toString();
//        String expected = "Developed by:";
//
//        assertEquals(actual,expected);
//        splashScreen.finish();
//    }

    @Test
    public void teamNameTextTest() {
        View view = splashScreen.findViewById(R.id.slogan2);
        assertNotNull(view);
    }

//    @Test
//    public void teamNameTextTest2(){
//        TextView textViewTest = splashScreen.findViewById(R.id.slogan2);
//        String actual = textViewTest.getText().toString();
//        String expected = "Ctrl Alt Elite";
//
//        assertEquals(actual,expected);
//        splashScreen.finish();
//    }

    @Test
    public void testLaunchItems(){
        View img = splashScreen.findViewById(R.id.WitsAcademyimg);
        View dev = splashScreen.findViewById(R.id.slogan1);
        View teamName = splashScreen.findViewById(R.id.slogan2);

        assertNotNull(img);
        assertNotNull(dev);
        assertNotNull(teamName);

        splashScreen.finish();
    }

    @After
    public void tearDown() throws Exception {
        splashScreen = null;
    }
}