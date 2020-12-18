package com.example.day12yue18;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager vap;
    private TabLayout tabMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        vap = (ViewPager) findViewById(R.id.vap);
        tabMode = (TabLayout) findViewById(R.id.tab);


        ArrayList<Fragment> list = new ArrayList<>();
        list.add(new ShouYeFragment());
        list.add(new FaXianFragment());
        list.add(new jiaHaoFragment());
        list.add(new ShangChengFragment());
        list.add(new MyFragment());
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(), list);
        vap.setAdapter(adapter);
        tabMode.setupWithViewPager(vap);
        tabMode.getTabAt(0).setText("首页").setIcon(R.drawable.icon_tab_home);
        tabMode.getTabAt(1).setText("沙发").setIcon(R.drawable.icon_tab_sofa);
        tabMode.getTabAt(2).setIcon(R.drawable.icon_tab_publish);
        tabMode.getTabAt(3).setText("发现").setIcon(R.drawable.icon_tab_find);
        tabMode.getTabAt(4).setText("我的").setIcon(R.drawable.icon_tab_mine);
    }
}
