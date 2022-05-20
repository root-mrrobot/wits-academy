package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {
    Handler handler;

    Animation topanim,bottomanim;
    ImageView image;
    TextView slogan1,slogan2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar

        setContentView(R.layout.activity_splash_screen);

        //initialising animation for logo and slogan
        topanim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomanim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        image = findViewById(R.id.WitsAcademyimg);
        slogan1 = findViewById(R.id.slogan1);
        slogan2 = findViewById(R.id.slogan2);

        //creating animation
        image.setAnimation(topanim);
        slogan1.setAnimation(bottomanim);
        slogan2.setAnimation(bottomanim);

        //creating splash screen.
        handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this, Login.class);
                startActivity(i);
                finish();
            }

        }, 2000);

    }
}