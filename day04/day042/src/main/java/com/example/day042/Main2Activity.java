package com.example.day042;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    private TabLayout tab;
    private ViewPager vp;
    private ArrayList<Fragment> list;
    private MyVpAdapter myVpAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
    }

    private void initView() {
        tab = (TabLayout) findViewById(R.id.tab);
        vp = (ViewPager) findViewById(R.id.vp);
        list = new ArrayList<>();
        list.add(new BlankFragment());
        list.add(new BlankFragment2());
        list.add(new BlankFragment3());
        myVpAdapter = new MyVpAdapter(getSupportFragmentManager(),list);
        vp.setAdapter(myVpAdapter);
        tab.setupWithViewPager(vp);
        tab.getTabAt(0).setText("袍子");
        tab.getTabAt(1).setText("社团");
        tab.getTabAt(2).setText("排行榜");
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        vp.setCurrentItem(id);
    }
}
