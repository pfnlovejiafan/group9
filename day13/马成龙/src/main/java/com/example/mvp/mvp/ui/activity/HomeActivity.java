package com.example.mvp.mvp.ui.activity;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.mvp.R;
import com.example.mvp.base.BaseActivity;
import com.example.mvp.base.BasePresenter;
import com.example.mvp.mvp.ui.fragment.BlankFragment;
import com.example.mvp.mvp.ui.fragment.BlankFragment2;
import com.example.mvp.mvp.ui.fragment.BlankFragment3;
import com.example.mvp.mvp.ui.fragment.BlankFragment4;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity {


    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.root_ll)
    LinearLayout rootLl;

    @Override
    protected void init() {
        List<Fragment> list=new ArrayList<>();
        list.add(new BlankFragment());
        list.add(new BlankFragment2());
        list.add(new BlankFragment3());
        list.add(new BlankFragment4());
        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        tab.setupWithViewPager(vp);
        tab.getTabAt(0).setText("首页").setIcon(R.drawable.select);
        tab.getTabAt(1).setText("选课").setIcon(R.drawable.select1);
        tab.getTabAt(2).setText("学习").setIcon(R.drawable.select2);
        tab.getTabAt(3).setText("我的").setIcon(R.drawable.select3);


    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
