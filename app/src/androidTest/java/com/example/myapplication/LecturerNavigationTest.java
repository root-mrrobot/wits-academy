package com.example.myapplication;

import static org.junit.Assert.*;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class LecturerNavigationTest {

    @Rule
    public ActivityTestRule<LecturerNavigation> lecturerNavigationTestRule = new ActivityTestRule<>(LecturerNavigation.class);

    @Before
    public void init(){
        lecturerNavigationTestRule.getActivity().getSupportFragmentManager().beginTransaction();
    }

    @Test
    public void testLaunch(){
        assertNotNull(lecturerNavigationTestRule.getActivity().getSupportFragmentManager().getFragments());
    }

/*/
    @Test
    public void lecturerHome(){
        lecturerNavigationTestRule.getActivity().findViewById(R.id.lecturerHome).performClick();
        assertNotNull(lecturerNavigationTestRule.getActivity().getSupportFragmentManager().findFragmentById(R.id.lecturerHomeFragment));
    }
*/

    @After
    public void tearDown(){
        lecturerNavigationTestRule.getActivity().finish();
    }



}