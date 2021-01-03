package com.example.biadumap_1.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.biadumap_1.R;
import com.example.biadumap_1.adapter.VpAdapter;
import com.example.biadumap_1.fragment.MapFragment1;
import com.example.biadumap_1.fragment.MapFragment2;
import com.example.biadumap_1.fragment.MapFragment3;
import com.example.biadumap_1.fragment.MapFragment4;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView text;
    private Toolbar toobar;
    private ViewPager vip_first;
    private TabLayout tab;
    private NavigationView nav;
    private DrawerLayout draw_main;
    private ArrayList<Fragment> fragments;
    private ArrayList<Integer> icons;
    private ArrayList<String> titles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        inittoolbar();
        initFragment();
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new MapFragment1());
        fragments.add(new MapFragment2());
        fragments.add(new MapFragment3());
        fragments.add(new MapFragment4());

        titles = new ArrayList<>();
        titles.add("页面1");
        titles.add("页面2");
        titles.add("页面3");
        titles.add("页面4");

        VpAdapter adapter = new VpAdapter(getSupportFragmentManager(), fragments, titles);
        vip_first.setAdapter(adapter);
        //设置联动
        tab.setupWithViewPager(vip_first);

        //准备图片资源
        icons = new ArrayList<>();
        icons.add(R.drawable.icons_selector);
        icons.add(R.drawable.icons_selector);
        icons.add(R.drawable.icons_selector);
        icons.add(R.drawable.icons_selector);

        for (int i = 0; i < fragments.size(); i++) {
            tab.getTabAt(i).setCustomView(getItemViews(i));
        }
    }

    private View getItemViews(int position) {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_tabitem, null);
        ImageView tabitem_img = view.findViewById(R.id.tabitem_img);
        TextView tabitem_text = view.findViewById(R.id.tabitem_text);
        tabitem_img.setImageResource(icons.get(position));
        tabitem_text.setText(titles.get(position));
        return view;
    }

    private void inittoolbar() {
        setSupportActionBar(toobar);
        toobar.setTitle("");
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, draw_main, toobar, R.string.app_name, R.string.app_name);
        draw_main.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void initView() {
        text = (TextView) findViewById(R.id.text);
        toobar = (Toolbar) findViewById(R.id.toobar);
        vip_first = (ViewPager) findViewById(R.id.vip_first);
        tab = (TabLayout) findViewById(R.id.tab);
        nav = (NavigationView) findViewById(R.id.nav);
        draw_main = (DrawerLayout) findViewById(R.id.draw_main);
    }



}