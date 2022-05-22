package com.example.myapplication;

import android.util.Patterns;

import java.util.regex.Pattern;

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

    /**
     * Email validation pattern.
     */
    public static final Pattern EMAIL_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );

    /**
     * Validates if the given input is a valid email address.
     *
     * @param email        The email to validate.
     * @return {@code true} if the input is a valid email. {@code false} otherwise.
     */
    public static boolean isValidEmail(CharSequence email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }
}
