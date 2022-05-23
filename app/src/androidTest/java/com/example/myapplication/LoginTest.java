package com.example.myapplication;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isNotChecked;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
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
public class LoginTest {

    @Rule
    public ActivityTestRule<Login> loginActivityTestRule = new ActivityTestRule<Login>(Login.class);

    private Login login = null;

    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(MainActivity.class.getName(),null ,false);
    Instrumentation.ActivityMonitor monitor2 = getInstrumentation().addMonitor(Home.class.getName(),null ,false);

    public static final String STRING_TO_BE_TYPED_FULL_NAME = "John Doe";
    public static final String STRING_TO_BE_TYPED_EMAIL = "johndoe@gmail.com";
    public static final String STRING_TO_BE_TYPED_PASSWORD = "jdoe123";
    public static final String STRING_TO_BE_TYPED_CONFIRM_PASSWORD = "jdoe123";

    @Before
    public void setUp() throws Exception {
        login = loginActivityTestRule.getActivity();
    }

    @Test
    public void isActivityInView(){
        onView(withId(R.id.login)).check(matches(isDisplayed()));
    }

    @Test
    public void loginTextTest() {
        View view = login.findViewById(R.id.loginTextView);
        assertNotNull(view);
    }

    @Test
    public void loginTextTest2(){
        TextView textViewTest = login.findViewById(R.id.loginTextView);
        String actual = textViewTest.getText().toString();
        String expected = "Login";

        assertEquals(actual,expected);
        login.finish();
    }

    @Test
    public void registerTextTest(){
        TextView textViewTest = login.findViewById(R.id.registerText);
        String actual = textViewTest.getText().toString();
        String expected = "Don't have an account? Register here";

        assertEquals(actual,expected);
        login.finish();
    }

    @Test
    public void registerPageBtnTest() {
        onView(withId(R.id.registerText)).perform(click());
    }

    @Test
    public void testLaunchItems(){
        View email = login.findViewById(R.id.email);
        View password = login.findViewById(R.id.password);
        assertNotNull(email);
        assertNotNull(password);

        login.finish();
    }

    @Test
    public void testReturnToRegisterPageButton(){
        onView(withId(R.id.registerText)).perform(click());
        Activity register = getInstrumentation().waitForMonitorWithTimeout(monitor,5000);
        assertNull(register);

        login.finish();
    }

    @Test
    public void testCheckBoxPwd(){
        onView(withId(R.id.showLoginPassword)).perform(click()).check(matches(ViewMatchers.isChecked()));
        login.finish();
    }

    @Test
    public void testCheckBoxPw2(){
        onView(withId(R.id.showLoginPassword)).perform(click());
    }

    @Test
    public void testCheckBoxPw3() {
        View view = login.findViewById(R.id.showLoginPassword);
        assertNotNull(view);
    }

    @Test
    public void testCheckBoxPwd4(){
        onView(withId(R.id.showLoginPassword)).perform(click()).check(matches(isChecked()));
        onView(withId(R.id.showLoginPassword)).perform(click()).check(matches(isNotChecked()));
        login.finish();
    }

    @Test
    public void testCheckBoxPw5(){
        onView(withId(R.id.showLoginPassword)).check(matches(isClickable()));
    }

    @Test
    public void testLoginButton(){
        onView(withId(R.id.email)).perform(typeText(STRING_TO_BE_TYPED_EMAIL), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText(STRING_TO_BE_TYPED_PASSWORD), closeSoftKeyboard());
        onView(withId(R.id.showLoginPassword)).perform(click());
        onView(withId(R.id.buttonLogin)).perform(click());

//        Activity login = getInstrumentation().waitForMonitorWithTimeout(monitor2,5000);

//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        onView(withId(R.id.recycler_view2)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
//        onView(withId(R.id.text_send)).perform(typeText("unit-testing sucks"), closeSoftKeyboard());
//        onView(withId(R.id.btn_send)).perform(click());
//        onView(withId(R.id.calls)).perform(click());
//        assertNotNull(login);
        login.finish();
    }

    @Test
    public void testLoginButton2(){
        onView(withId(R.id.buttonLogin)).perform(click());
    }

    @Test
    public void testLoginButton3(){
        View view = login.findViewById(R.id.buttonLogin);
        assertNotNull(view);
    }

    @Test
    public void testLoginButton4(){
        onView(withId(R.id.buttonLogin)).perform(click());
        Activity login = getInstrumentation().waitForMonitorWithTimeout(monitor,5000);
        assertNull(login);

        //login.finish();
    }

    @Test
    public void testLoginButton5(){
        TextView textViewTest = login.findViewById(R.id.buttonLogin);
        String actual = textViewTest.getText().toString();
        String expected = "Login";

        assertEquals(actual,expected);
        login.finish();
    }

    @Test
    public void testNegative1LoginButton(){
        onView(withId(R.id.buttonLogin)).perform(click());
        String check = login.unitTest;
        assertEquals(check,"True");
        login.finish();
    }

    @Test
    public void testNegative2LoginButton(){
        onView(withId(R.id.email)).perform(typeText(STRING_TO_BE_TYPED_EMAIL), closeSoftKeyboard());
        onView(withId(R.id.buttonLogin)).perform(click());
        String check = login.unitTest;
        assertEquals(check,"True");
        login.finish();
    }

    @Test
    public void testNegative4LoginButton(){
        onView(withId(R.id.password)).perform(typeText(STRING_TO_BE_TYPED_PASSWORD), closeSoftKeyboard());
        onView(withId(R.id.buttonLogin)).perform(click());
        String check = login.unitTest;
        assertEquals(check,"True");
        login.finish();
    }

    @Test
    public void testForgotPwButton(){
        onView(withId(R.id.forgotPassword)).perform(click());
    }

    @Test
    public void testForgotButton2(){
        onView(withId(R.id.forgotPassword)).perform(click());
        Activity login = getInstrumentation().waitForMonitorWithTimeout(monitor,5000);
        assertNull(login);

        //login.finish();
    }

    @Test
    public void testForgotButtonDialogTitle(){
        onView(withId(R.id.forgotPassword)).perform(click());
        onView(withText("Reset Password?")).check(matches(isDisplayed()));
    }

    @Test
    public void testForgotButtonDialogMsg(){
        onView(withId(R.id.forgotPassword)).perform(click());
        onView(withText("Enter your email to receive reset password link")).check(matches(isDisplayed()));
    }

    @Test
    public void testForgotButtonDialogOptions(){
        onView(withId(R.id.forgotPassword)).perform(click());
        onView(withId(android.R.id.button1)).check(matches(isDisplayed()));
        onView(withId(android.R.id.button2)).check(matches(isDisplayed()));
    }

    @Test
    public void testForgotClickCancelBtn(){
        onView(withId(R.id.forgotPassword)).perform(click());
        // android.R.id.button2 = negative button (Cancel)
        onView(withId(android.R.id.button2)).perform(click());
    }

    @After
    public void tearDown() throws Exception {
        login = null;
    }
}