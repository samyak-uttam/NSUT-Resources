package com.example.nsutallin1.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.example.nsutallin1.Adapter.CompetitiveViewPagerAdapter;
import com.example.nsutallin1.Adapter.ViewPagerAdapter;
import com.example.nsutallin1.R;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;


import com.example.nsutallin1.Adapter.ViewPagerAdapter;
import com.example.nsutallin1.R;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;


public class CompetitiveProgrammingActivity extends AppCompatActivity {

        private ViewPager viewPager;
        private TabLayout tabLayout;
        private CompetitiveViewPagerAdapter competitiveViewPagerAdapter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_competitive_programming);

            Toolbar toolbar = findViewById(R.id.toolbar_competitive);
            setSupportActionBar(toolbar);

            viewPager = findViewById(R.id.view_pager_competitive);
            competitiveViewPagerAdapter = new CompetitiveViewPagerAdapter(getSupportFragmentManager());
            viewPager.setAdapter(competitiveViewPagerAdapter);
            tabLayout = findViewById(R.id.tabs_competitive);

            tabLayout.setupWithViewPager(viewPager);
        }

    }


