package com.example.nsutallin1.Adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.nsutallin1.Fragments.AboutDevelopersFragment;
import com.example.nsutallin1.Fragments.CollegeFragment;
import com.example.nsutallin1.Fragments.ImsFragment;
import com.example.nsutallin1.Fragments.SyllabusFragment;

import java.util.ArrayList;


public class ViewPagerAdapter extends FragmentPagerAdapter {
    ArrayList <Fragment> fragmentsInTabView=new ArrayList<Fragment>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);

        fragmentsInTabView.add(new CollegeFragment());
        fragmentsInTabView.add(new SyllabusFragment());
        fragmentsInTabView.add(new ImsFragment());
        fragmentsInTabView.add(new AboutDevelopersFragment());

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

        switch (position)
        {
            case 0:
                return "Home";

            case 1:
                return "Syllabus";

            case 2:
                return "College News";

            case 3:
                return "About";
                default:
                    return "null";
        }
    }
}
