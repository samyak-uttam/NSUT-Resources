package com.educational.nsutresources.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


import androidx.appcompat.app.AppCompatActivity;

import com.educational.nsutresources.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class SplashScreenActivity extends AppCompatActivity {

    private GoogleSignInClient mGoogleSignInClient;

    private static final long SPLASH_DISPLAY_LENGTH = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Check if user has already signed in
                GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(SplashScreenActivity.this);
                if (account != null) {
                    Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                } else {
                    /* Create an Intent that will start the Menu-Activity. */
                    Intent mainIntent = new Intent(SplashScreenActivity.this, SigninActivity.class);
                    mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(mainIntent);
                    finish();
                }
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}