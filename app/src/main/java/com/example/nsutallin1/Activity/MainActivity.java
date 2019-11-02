package com.example.nsutallin1.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.nsutallin1.Adapter.ViewPagerAdapter;
import com.example.nsutallin1.R;
import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawer;
    private ViewPager viewPager;
    //private Toolbar toolbar;
    private TabLayout tabLayout;
    ViewPagerAdapter viewPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.view_pager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout=findViewById(R.id.tabs);

        tabLayout.setupWithViewPager(viewPager);
    }

}

