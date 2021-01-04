package com.example.project.engine.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;


import com.example.project.base.App;

import java.util.List;

public class HomeActVpAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragments;
    private final int[] mTabs;

    public HomeActVpAdapter(@NonNull FragmentManager fm, List<Fragment> fragments, int[] mTabs) {
        super(fm);
        this.mFragments = fragments;
        this.mTabs = mTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return App.getStr(mTabs[position]);
    }
}
