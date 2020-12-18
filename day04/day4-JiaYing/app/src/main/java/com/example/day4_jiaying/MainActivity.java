package com.example.day4_jiaying;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.day4_jiaying.adapter.VpAdapter;
import com.example.day4_jiaying.fragment.FindFragment;
import com.example.day4_jiaying.fragment.HomeFragment;
import com.example.day4_jiaying.fragment.MeFragment;
import com.example.day4_jiaying.fragment.SerachFragment;
import com.example.day4_jiaying.fragment.ShopFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager vip;
    private TabLayout tab;
    private ArrayList<Fragment> fragments;
    private ArrayList<String> strings;
    private VpAdapter adapter;
    private ArrayList<Integer> icons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        vip = (ViewPager) findViewById(R.id.vip);
        tab = (TabLayout) findViewById(R.id.tab);

        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new FindFragment());
        fragments.add(new SerachFragment());
        fragments.add(new ShopFragment());
        fragments.add(new MeFragment());

        strings = new ArrayList<>();
        strings.add("首页");
        strings.add("发现");
        strings.add("");
        strings.add("商城");
        strings.add("我的");

        adapter = new VpAdapter(getSupportFragmentManager(), fragments, strings);
        vip.setAdapter(adapter);
        tab.setupWithViewPager(vip);

        icons = new ArrayList<>();
        icons.add(R.drawable.home_selector);
        icons.add(R.drawable.home_selector);
        icons.add(R.drawable.icon_tab_publish);
        icons.add(R.drawable.home_selector);
        icons.add(R.drawable.home_selector);
        for (int i = 0; i < fragments.size(); i++) {
            tab.getTabAt(i).setCustomView(getItemViews(i));
        }
    }

    private View getItemViews(int position) {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_tabitem, null);
        TextView tabitem_text = view.findViewById(R.id.tabitem_text);
        ImageView tabitem_img = view.findViewById(R.id.tabitem_img);
        tabitem_img.setImageResource(icons.get(position));
        tabitem_text.setText(strings.get(position));
        return view;
    }
}