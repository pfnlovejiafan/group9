package com.example.demo_03.view.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.demo_03.bean.TabBean;

import java.util.ArrayList;

class VpAdapter extends FragmentStatePagerAdapter {
    private ArrayList<TabBean.DataBean> strings;
    private ArrayList<Fragment> list;

    public VpAdapter(@NonNull FragmentManager fm, ArrayList<TabBean.DataBean> strings, ArrayList<Fragment> list) {
        super(fm);
        this.strings = strings;
        this.list = list;
    }

    public VpAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return strings.get(position).getName();
    }
}
