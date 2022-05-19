package com.example.myapplication;

import android.util.Patterns;

public class RegisterValidation {

    // check that string is not empty
    public static boolean notEmptyString(String test){

        return (!test.isEmpty());

    }

    // check that string matches the email pattern
    public static boolean matchesEmailPattern(String test){

        return (Patterns.EMAIL_ADDRESS.matcher(test).matches());

    }

    // check that password(string) has 6+ characters
    public static boolean lengthGreaterThanSix(String test){

        return (test.length() >= 6);

    }

    // check that password(string1) matches confirmPassword(string2)
    public static boolean passwordMatchesConfirm(String test1, String test2){

        return (test1.equals(test2));

    }
}
