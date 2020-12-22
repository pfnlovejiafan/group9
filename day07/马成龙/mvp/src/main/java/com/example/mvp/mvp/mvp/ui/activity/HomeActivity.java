package com.example.mvp.mvp.mvp.ui.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.mvp.R;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.home_act_vp)
    ViewPager homeActVp;
    @BindView(R.id.home_act_tab)
    TabLayout homeActTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        homeActTab.addTab(homeActTab.newTab().setText("首页").setIcon(R.drawable.home_tab));
        homeActTab.addTab(homeActTab.newTab().setText("发现").setIcon(R.drawable.know_tab));
        homeActTab.addTab(homeActTab.newTab().setText("").setIcon(R.drawable.e));
        homeActTab.addTab(homeActTab.newTab().setText("商城").setIcon(R.drawable.shop_tab));
        homeActTab.addTab(homeActTab.newTab().setText("我的").setIcon(R.drawable.d));
    }
}
