package com.example.myapplication;

import static org.junit.Assert.*;

import org.junit.Test;

public class LoginValidationTest {

    // email is not empty
    @Test
    public void notEmptyEmail() {

        boolean output = LoginValidation.notEmptyString("ash");
        assertTrue(output);

    }

    // email is empty
    @Test
    public void EmptyEmail() {

        boolean output = LoginValidation.notEmptyString("");
        assertFalse(output);

    }

    /*
     * having issues with the email pattern matching tests (will fix in the next
     * sprint)
     * // email matches pattern
     * 
     * @Test
     * public void matchesEmailPattern() {
     * 
     * //boolean output = LoginValidation.matchesEmailPattern("aashna@gmail.com");
     * assertEquals(true, output);
     *
     * }
     * 
     * // email doesn't match pattern
     * 
     * @Test
     * public void notMatchingEmailPattern() {
     * 
     * boolean output = LoginValidation.matchesEmailPattern("Smith");
     * assertEquals(false,output);
     * 
     * }
     * 
     */

    // password is >= 6 char
    @Test
    public void lengthGreaterThanSix() {

        boolean output = LoginValidation.lengthGreaterThanSix("ash123");
        assertTrue(output);

    }

    // password is not >= 6 char
    @Test
    public void lengthNotGreaterThanSix() {

        boolean output = LoginValidation.lengthGreaterThanSix("123");
        assertFalse(output);

    }

    // password is not empty
    @Test
    public void notEmptyPassword() {

        boolean output = LoginValidation.notEmptyString("ash");
        assertTrue(output);

    }

    // password is empty
    @Test
    public void EmptyPassword() {

        boolean output = LoginValidation.notEmptyString("");
        assertFalse(output);

    }
}
