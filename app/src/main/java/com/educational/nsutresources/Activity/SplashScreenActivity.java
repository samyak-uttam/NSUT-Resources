package com.educational.nsutresources.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import com.educational.nsutresources.Class.Contest;
import com.educational.nsutresources.Loader.DsAlgoLoader;
import com.educational.nsutresources.R;

import java.util.ArrayList;

public class SplashScreenActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Contest>>{

    private static final long SPLASH_DISPLAY_LENGTH = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(SplashScreenActivity.this, MainActivity.class);
                mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(mainIntent);
                finish();

            }
        }, SPLASH_DISPLAY_LENGTH);

        LoaderManager loaderManager = getSupportLoaderManager();
        loaderManager.initLoader(1, null, this);
    }


    @NonNull
    @Override
    public Loader<ArrayList<Contest>> onCreateLoader(int id, @Nullable Bundle args) {
        return new DsAlgoLoader(this, 2);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<Contest>> loader, ArrayList<Contest> data) {

    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<Contest>> loader) {

    }
}