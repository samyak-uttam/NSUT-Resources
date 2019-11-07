package com.example.nsutallin1.Adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.nsutallin1.Fragments.CollegeFragment;
import com.example.nsutallin1.Fragments.DownloadsFragment;
import com.example.nsutallin1.Fragments.ImsFragment;

import java.util.ArrayList;


public class ViewPagerAdapter extends FragmentPagerAdapter {
    ArrayList <Fragment> fragmentsInTabView=new ArrayList<Fragment>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);

        fragmentsInTabView.add(new CollegeFragment());
        fragmentsInTabView.add(new DownloadsFragment());
        fragmentsInTabView.add(new ImsFragment());

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
                return "Downloads";

            case 2:
                return "College News";

                default:
                    return "null";
        }
    }
}
