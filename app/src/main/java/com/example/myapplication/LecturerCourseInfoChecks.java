package com.example.myapplication;

import android.app.Activity;
import androidx.activity.result.ActivityResult;
import android.net.Uri;
import com.google.firebase.auth.FirebaseUser;

public class LecturerCourseInfoChecks {

    // check if user is null
    public static boolean noUser(FirebaseUser testUser){

        return (testUser == null);

    }

    // check if activity result is successful
    public static boolean successfulActivityResult(ActivityResult testActivityResult){
        return (testActivityResult.getResultCode() == Activity.RESULT_OK);
    }


    // check if String is empty
    public static boolean emptyString(String testString){

        return (testString.isEmpty());

    }

    // check if image has been selected
    public static boolean noImage(Uri testUri){

        return (testUri == null);

    }

}
