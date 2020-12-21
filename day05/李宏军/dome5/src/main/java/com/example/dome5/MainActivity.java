package com.example.dome5;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tab2)
    TabLayout tab2;
    @BindView(R.id.tab1)
    TabLayout tab1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        tab2.addTab(tab2.newTab().setText("每日推荐").setIcon(R.drawable.a1));
        tab2.addTab(tab2.newTab().setText("飞花令").setIcon(R.drawable.a2));
        tab2.addTab(tab2.newTab().setText("诗歌社群").setIcon(R.drawable.a3));
        tab2.addTab(tab2.newTab().setText("排行榜").setIcon(R.drawable.a4));
        tab2.addTab(tab2.newTab().setText("会员专区").setIcon(R.drawable.a5));

        tab1.addTab(tab1.newTab().setText("首页").setIcon(R.drawable.shouye));
        tab1.addTab(tab1.newTab().setText("写诗").setIcon(R.drawable.edit));
        tab1.addTab(tab1.newTab().setText("传图").setIcon(R.drawable.paizhao));
        tab1.addTab(tab1.newTab().setText("作品").setIcon(R.drawable.dingdan));
        tab1.addTab(tab1.newTab().setText("我的").setIcon(R.drawable.wode));
    }
}
