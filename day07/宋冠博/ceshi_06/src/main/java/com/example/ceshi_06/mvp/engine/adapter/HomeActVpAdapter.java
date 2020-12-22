package com.example.ceshi_06.mvp.engine.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.ceshi_06.mvp.base.App;

import java.util.ArrayList;
import java.util.List;

public class HomeActVpAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragments;
    private ArrayList<Integer> list;

    public HomeActVpAdapter(@NonNull FragmentManager fm, List<Fragment> mFragments, ArrayList<Integer> list) {
        super(fm);
        this.mFragments = mFragments;
        this.list = list;
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

    @Override
    public CharSequence getPageTitle(int position) {

        return App.getStr(list.get(position));
    }
}