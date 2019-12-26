package com.educational.nsutresources.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.educational.nsutresources.Fragments.RunningContestsFragment;
import com.educational.nsutresources.Fragments.UpcomingContestsFragment;

import java.util.ArrayList;

public class CompetitiveViewPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragmentsInTabViewCompetitve = new ArrayList<>();

    public CompetitiveViewPagerAdapter(FragmentManager fm) {
        super(fm);

        fragmentsInTabViewCompetitve.add(new RunningContestsFragment());
        fragmentsInTabViewCompetitve.add(new UpcomingContestsFragment());
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentsInTabViewCompetitve.get(position);
    }

    @Override
    public int getCount() {
        return fragmentsInTabViewCompetitve.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case 0:
                return "Running";
            case 1:
                return "Upcomming";
            default:
                return "null";
        }
    }

}
