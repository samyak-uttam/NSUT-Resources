package com.educational.nsutresources.Activity;

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


import com.educational.nsutresources.Adapter.ViewPagerAdapter;
import com.educational.nsutresources.R;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ViewPagerAdapter viewPagerAdapter;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.view_pager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout = findViewById(R.id.tabs);

        tabLayout.setupWithViewPager(viewPager);

        navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int selectedItem=menuItem.getItemId();

        switch (selectedItem)
        {
            case R.id.nav_aboutdevelopers:
                Intent intent = new Intent(this,AboutDevelopersActivity.class);
                startActivity(intent);
                break;
            case R.id.syllabus:
                Intent intent1 = new Intent(this, SyllabusActivity.class);
                startActivity(intent1);
                break;
            case R.id.nav_notices:
                Intent intent2 = new Intent(this, NoticesActivity.class);
                startActivity(intent2);
                break;
            case R.id.competetive_coding:
                Intent intent3 = new Intent(this, CompetitiveProgrammingActivity.class);
                startActivity(intent3);
        }

        drawer.closeDrawer(GravityCompat.START,false);
        return true;
    }
}

