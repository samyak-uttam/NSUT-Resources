package com.educational.nsutresources.Activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.educational.nsutresources.Adapter.CompetitiveViewPagerAdapter;
import com.educational.nsutresources.R;
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

        getSupportActionBar().setTitle("Contests");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        viewPager = findViewById(R.id.view_pager_competitive);
        competitiveViewPagerAdapter = new CompetitiveViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(competitiveViewPagerAdapter);
        tabLayout = findViewById(R.id.tabs_competitive);

        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home)
        {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}


