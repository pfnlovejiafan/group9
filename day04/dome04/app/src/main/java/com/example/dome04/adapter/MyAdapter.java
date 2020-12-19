package com.example.dome04.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class MyAdapter extends FragmentPagerAdapter {

    private final List<Fragment> fragments;
    private final List<String> tabs;

    public MyAdapter(FragmentManager supportFragmentManager, List<Fragment> fragments, List<String> tabs) {
        super(supportFragmentManager);
        this.fragments = fragments;
        this.tabs = tabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (fragments != null && fragments.size() > 0) {
            return fragments.get(position);
        }
        return null;
    }

    @Override
    public int getCount() {
        if (fragments != null && fragments.size() > 0) {
            return fragments.size();
        }
        return 0;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (tabs != null && tabs.size() > 0) {
            return tabs.get(position);
        }
        return null;
    }
}
