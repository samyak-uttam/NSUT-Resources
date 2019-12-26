package com.educational.nsutresources.Adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.educational.nsutresources.Fragments.CollegeFragment;
import com.educational.nsutresources.Fragments.SavedFragment;

import java.util.ArrayList;


public class ViewPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragmentsInTabView = new ArrayList<Fragment>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);

        fragmentsInTabView.add(new CollegeFragment());
        fragmentsInTabView.add(new SavedFragment());
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentsInTabView.get(position);
    }

    @Override
    public int getCount() {
        return fragmentsInTabView.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case 0:
                return "Home";
            case 1:
                return "Saved";
            default:
                return "null";
        }
    }
}
