package com.example.dome04;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.dome04.adapter.MyAdapter;
import com.example.dome04.fragment.FoundFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager mTpVp;
    private TabLayout mBottomTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        List<String> tabs = new ArrayList<>();
        List<Fragment> fragments = new ArrayList<>();
//        tabs.add("首页");
        tabs.add("发现");
//        tabs.add("");
//        tabs.add("商城");
//        tabs.add("我的");

//        fragments.add(new HomeFragment());
        fragments.add(new FoundFragment());

        //tablayout横向滚动
        mBottomTab.setTabMode(TabLayout.MODE_SCROLLABLE);
        //初始化ViewPager
        mTpVp.setAdapter(new MyAdapter(getSupportFragmentManager(), fragments, tabs));
        mBottomTab.setupWithViewPager(mTpVp);

    }

    private void initView() {
        mTpVp = (ViewPager) findViewById(R.id.vp_tp);
        mBottomTab = (TabLayout) findViewById(R.id.tab_bottom);

    }
}