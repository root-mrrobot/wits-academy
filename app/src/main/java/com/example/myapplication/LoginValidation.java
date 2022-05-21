package com.example.myapplication;

import android.util.Patterns;

public class LoginValidation {

    // check that string is not an empty string
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

    /*
    // check that password has at least one number
    public static boolean hasNumber(String test){

        return (test.matches(".*\\d+.*"));
    }

    // check that password has at least one upper case letter
    public static boolean hasUpperCase(String test){

        return (test.matches(".*[A-Z]+.*"));
    }

    // check that password has at least one lower case letter
    public static boolean hasLowerCase(String test){

        return (test.matches(".*[a-z]+.*"));
    }

    // check that password has at least one special character
    public static boolean hasSpecialCharacter(String test){

        return (test.matches(".*[!@#$%^&*()_+=]+.*"));
    }
    */

}

