package com.example.smartlibrary20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    private static int splashTimer = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        if(mUser == null)
            startActivity(new Intent(SplashActivity.this,MainActivity.class));
        else
            startActivity(new Intent(SplashActivity.this,HomeScreen.class));

        finish();
            }
    },splashTimer);

    }
}