package com.example.demo_03.view.activity;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.demo_03.R;
import com.example.demo_03.view.fragment.Zhu_AFragment;
import com.example.demo_03.view.fragment.Zhu_BFragment;
import com.example.demo_03.view.fragment.Zhu_CFragment;
import com.example.demo_03.view.fragment.Zhu_DFragment;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private TextView tv;
    private Toolbar toolbar;
    private FrameLayout fl;
    private TabLayout tab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        tv = (TextView) findViewById(R.id.tv);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        fl = (FrameLayout) findViewById(R.id.fl);
        tab = (TabLayout) findViewById(R.id.tab);

        tv.setText("首页");
        setSupportActionBar(toolbar);

        tab.addTab(tab.newTab().setText("首页").setIcon(R.drawable.aa));
        tab.addTab(tab.newTab().setText("发现").setIcon(R.drawable.bb));
        tab.addTab(tab.newTab().setIcon(R.drawable.cc));
        tab.addTab(tab.newTab().setText("收藏").setIcon(R.drawable.dd));
        tab.addTab(tab.newTab().setText("我的").setIcon(R.drawable.ee));

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl,new Zhu_AFragment())
                .commit();

        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if (position==0){
                    tv.setText("首页");
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fl,new Zhu_AFragment())
                            .commit();
                }
                if (position==1){
                    tv.setText("发现");
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fl,new Zhu_BFragment())
                            .commit();
                }
                if (position==3){
                    tv.setText("收藏");
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fl,new Zhu_CFragment())
                            .commit();
                }
                if (position==4){
                    tv.setText("我的");
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fl,new Zhu_DFragment())
                            .commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
