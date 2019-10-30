package com.example.nsutallin1.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.nsutallin1.Fragments.HomeFragment;
import com.example.nsutallin1.Fragments.NotesFragment;
import com.example.nsutallin1.Fragments.PapersFragment;
import com.example.nsutallin1.R;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

       if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.framgment_container,
                    new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.home);
        }
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_praciticals:
                getSupportFragmentManager().beginTransaction().replace(R.id.framgment_container,
                        new HomeFragment()).commit();
                break;
            case R.id.nav_notes:
                getSupportFragmentManager().beginTransaction().replace(R.id.framgment_container,
                        new NotesFragment()).commit();
                break;
            case R.id.nav_papers:
                getSupportFragmentManager().beginTransaction().replace(R.id.framgment_container,
                        new PapersFragment()).commit();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
