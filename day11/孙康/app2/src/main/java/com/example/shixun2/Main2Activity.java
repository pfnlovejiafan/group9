package com.example.shixun2;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    private Toolbar too;
    private ViewPager vp;
    private TabLayout tab;
    private MyFramgentAdapter myFramgentAdapter;
    private ArrayList<Fragment> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
    }

    private void initView() {
        too = (Toolbar)findViewById(R.id.too);
        setSupportActionBar(too);
        vp = (ViewPager)findViewById(R.id.vp);
        tab = (TabLayout)findViewById(R.id.tab);
        list = new ArrayList<>();
        list.add(new BlankFragment());
        list.add(new BlankFragment2());
        list.add(new BlankFragment3());
        myFramgentAdapter = new MyFramgentAdapter(getSupportFragmentManager(),list);
        vp.setAdapter(myFramgentAdapter);
        tab.setupWithViewPager(vp);
        tab.getTabAt(0).setText("首页").setIcon(R.drawable.a);
        tab.getTabAt(1).setText("攻略").setIcon(R.drawable.e);
        tab.getTabAt(2).setText("我的").setIcon(R.drawable.g);
    }
}
