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
public class RegisterTest {

    @Rule
    public ActivityTestRule<Register> registerActivityTestRule = new ActivityTestRule<Register>(Register.class);

    private Register register = null;

    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(MainActivity.class.getName(),null ,false);

    public static final String STRING_TO_BE_TYPED_FULL_NAME = "John Doe";
    public static final String STRING_TO_BE_TYPED_EMAIL = "johndoe@gmail.com";
    public static final String STRING_TO_BE_TYPED_PASSWORD = "jdoe123";
    public static final String STRING_TO_BE_TYPED_CONFIRM_PASSWORD = "jdoe123";

    @Before
    public void setUp() throws Exception {
        register = registerActivityTestRule.getActivity();
    }

    @Test
    public void registerTextTest() {
        View view = register.findViewById(R.id.registerTextView);
        assertNotNull(view);
    }

    @Test
    public void registerTextTest2(){
        TextView textViewTest = register.findViewById(R.id.registerTextView);
        String actual = textViewTest.getText().toString();
        String expected = "Register";

        assertEquals(actual,expected);
        register.finish();
    }

    @Test
    public void loginTextTest(){
        TextView textViewTest = register.findViewById(R.id.loginText);
        String actual = textViewTest.getText().toString();
        String expected = "Already have an account? Login here";

        assertEquals(actual,expected);
        register.finish();
    }

    @Test
    public void testLaunchItems(){
        View fullName = register.findViewById(R.id.fullName);
        View email = register.findViewById(R.id.email);
        View password = register.findViewById(R.id.password);
        View confirmPassword = register.findViewById(R.id.confirmPassword);
        View showRegisterPassword = register.findViewById(R.id.showRegisterPassword);
        View btnRegister = register.findViewById(R.id.btnRegister);

        assertNotNull(fullName);
        assertNotNull(email);
        assertNotNull(password);
        assertNotNull(confirmPassword);
        assertNotNull(showRegisterPassword);
        assertNotNull(btnRegister);

        register.finish();
    }

    @Test
    public void testReturnToLoginPageButton(){
        onView(withId(R.id.loginText)).perform(click());
        Activity login = getInstrumentation().waitForMonitorWithTimeout(monitor,5000);
        assertNull(login);

        register.finish();
    }

    @Test
    public void testCheckBoxPwd(){
        onView(withId(R.id.showRegisterPassword)).perform(click()).check(matches(ViewMatchers.isChecked()));
        register.finish();
    }

    @Test
    public void testRegisterButton(){
        onView(withId(R.id.fullName)).perform(typeText(STRING_TO_BE_TYPED_FULL_NAME), closeSoftKeyboard());
        onView(withId(R.id.email)).perform(typeText(STRING_TO_BE_TYPED_EMAIL), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText(STRING_TO_BE_TYPED_PASSWORD), closeSoftKeyboard());
        onView(withId(R.id.confirmPassword)).perform(typeText(STRING_TO_BE_TYPED_CONFIRM_PASSWORD), closeSoftKeyboard());

        onView(withId(R.id.btnRegister)).perform(click());
        String check = register.unitTest;
        assertEquals(check,"True");
        register.finish();
    }

    @Test
    public void testNegative1RegisterButton(){
        onView(withId(R.id.btnRegister)).perform(click());
        String check = register.unitTest;
        assertEquals(check,"True");
        register.finish();
    }

    @Test
    public void testNegative2RegisterButton(){
        onView(withId(R.id.fullName)).perform(typeText(STRING_TO_BE_TYPED_FULL_NAME), closeSoftKeyboard());
        onView(withId(R.id.btnRegister)).perform(click());
        String check = register.unitTest;
        assertEquals(check,"True");
        register.finish();
    }

    @Test
    public void testNegative3RegisterButton(){
        onView(withId(R.id.email)).perform(typeText(STRING_TO_BE_TYPED_EMAIL), closeSoftKeyboard());
        onView(withId(R.id.btnRegister)).perform(click());
        String check = register.unitTest;
        assertEquals(check,"True");
        register.finish();
    }

    @Test
    public void testNegative4RegisterButton(){
        onView(withId(R.id.password)).perform(typeText(STRING_TO_BE_TYPED_PASSWORD), closeSoftKeyboard());
        onView(withId(R.id.btnRegister)).perform(click());
        String check = register.unitTest;
        assertEquals(check,"True");
        register.finish();
    }

    @Test
    public void testNegative5RegisterButton(){
        onView(withId(R.id.confirmPassword)).perform(typeText(STRING_TO_BE_TYPED_CONFIRM_PASSWORD), closeSoftKeyboard());
        onView(withId(R.id.btnRegister)).perform(click());
        String check = register.unitTest;
        assertEquals(check,"True");
        register.finish();
    }

    @Test
    public void testNegative6RegisterButton(){
        onView(withId(R.id.password)).perform(typeText(STRING_TO_BE_TYPED_PASSWORD), closeSoftKeyboard());
        onView(withId(R.id.confirmPassword)).perform(typeText(STRING_TO_BE_TYPED_CONFIRM_PASSWORD), closeSoftKeyboard());
        onView(withId(R.id.btnRegister)).perform(click());
        String check = register.unitTest;
        assertEquals(check,"True");
        register.finish();
    }

// Can't implement this because we done it differently (Can be used in the future maybe)
//    @Test
//    public void testRegister(){
//        register.registerUser(STRING_TO_BE_TYPED_FULL_NAME, STRING_TO_BE_TYPED_EMAIL, STRING_TO_BE_TYPED_PASSWORD, STRING_TO_BE_TYPED_CONFIRM_PASSWORD);
//
//        register.finish();
//    }

    @After
    public void tearDown() throws Exception {
        register = null;
    }
}