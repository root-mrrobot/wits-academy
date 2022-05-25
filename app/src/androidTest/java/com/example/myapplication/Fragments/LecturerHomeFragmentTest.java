package com.example.myapplication.Fragments;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.not;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.android21buttons.fragmenttestrule.FragmentTestRule;
import com.example.myapplication.LecturerHomeFragment;
import com.example.myapplication.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class LecturerHomeFragmentTest {

    // Fragment Test Rule for Lecturer Account Fragment
    @Rule
    public FragmentTestRule<?, LecturerHomeFragment> lecturerHomeFragmentTestRule = FragmentTestRule.create(LecturerHomeFragment.class);
    //LecturerHomeFragment lecturerHomeFragment = lecturerHomeFragmentTestRule.getFragment();

    // Checking if the activity appears and whether is matches the id 'lecturerAccountFragment'
    @Test
    public void isActivityInView(){
        onView(withId(R.id.lecturerHomeFragment)).check(matches(not(isDisplayed())));
    }
}
