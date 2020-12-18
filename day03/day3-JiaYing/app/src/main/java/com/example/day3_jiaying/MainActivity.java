package com.example.day3_jiaying;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.day3_jiaying.adapter.VpAdapter;
import com.example.day3_jiaying.fragment.FindFragment;
import com.example.day3_jiaying.fragment.HomeFragment;
import com.example.day3_jiaying.fragment.MeFragment;
import com.example.day3_jiaying.fragment.ShaFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TabLayout tab;
    private ViewPager vip_first;
    private ArrayList<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        tab = (TabLayout) findViewById(R.id.tab);
        vip_first = (ViewPager) findViewById(R.id.vip_first);

        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new ShaFragment());
        fragments.add(new FindFragment());
        fragments.add(new MeFragment());

        ArrayList<String> titles = new ArrayList<>();
        titles.add("首页");
        titles.add("沙发");
        titles.add("发现");
        titles.add("我的");

        VpAdapter adapter = new VpAdapter(getSupportFragmentManager(), fragments, titles);
        vip_first.setAdapter(adapter);
        tab.setupWithViewPager(vip_first);
    }
}