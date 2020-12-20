package com.example.ceshi_02;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TabLayout tab1;
    private TabLayout tab2;
    private ImageView iv;
    private RecyclerView rv_a;
    private RvA_Adapter rvA_adapter;
    private ArrayList<String> strings;
    private ArrayList<Integer> list;
    private RecyclerView rv_b;
    private RecyclerView rv_c;
    private RecyclerView rv_d;
    private RvB_Adapter rvB_adapter;
    private ArrayList<Integer> list1;
    private ArrayList<String> strings1;
    private RvC_Adapter rvC_adapter;
    private RvD_Adapter rvD_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        tab1 = (TabLayout) findViewById(R.id.tab1);
        tab2 = (TabLayout) findViewById(R.id.tab2);
        rv_a = (RecyclerView) findViewById(R.id.rv_a);
        rv_b = (RecyclerView) findViewById(R.id.rv_b);
        rv_c = (RecyclerView) findViewById(R.id.rv_c);
        rv_d = (RecyclerView) findViewById(R.id.rv_d);
        tab();

        rv_a();

        rv_b();

        rv_c();

        rv_d.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        rvD_adapter = new RvD_Adapter(this);
        rv_d.setAdapter(rvD_adapter);
    }

    private void rv_c() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_c.setLayoutManager(linearLayoutManager);
        rvC_adapter = new RvC_Adapter(this);
        rv_c.setAdapter(rvC_adapter);
    }

    private void rv_b() {
        strings1 = new ArrayList<>();
        strings1.add("20");
        strings1.add("8");
        strings1.add("1");
        list1 = new ArrayList<>();
        list1.add(R.drawable.d1);
        list1.add(R.drawable.d2);
        list1.add(R.drawable.d3);
        rv_b.setLayoutManager(new LinearLayoutManager(this));
        rvB_adapter = new RvB_Adapter(this,strings1,list1);
        rv_b.setAdapter(rvB_adapter);
    }

    private void rv_a() {
        rv_a.setLayoutManager(new GridLayoutManager(this, 5));
        strings = new ArrayList<>();
        strings.add("seastar");
        strings.add("风柒");
        strings.add("清芷");
        strings.add("花花娘子");
        strings.add("斜阳未冷");
        strings.add("seastar");
        strings.add("风柒");
        strings.add("清芷");
        strings.add("花花娘子");
        strings.add("斜阳未冷");
        list = new ArrayList<>();
        list.add(R.drawable.c1);
        list.add(R.drawable.c2);
        list.add(R.drawable.c3);
        list.add(R.drawable.c4);
        list.add(R.drawable.c5);
        list.add(R.drawable.c1);
        list.add(R.drawable.c2);
        list.add(R.drawable.c3);
        list.add(R.drawable.c4);
        list.add(R.drawable.c5);
        rvA_adapter = new RvA_Adapter(this, strings, list);
        rv_a.setAdapter(rvA_adapter);
    }

    private void tab() {
        tab1.addTab(tab1.newTab().setText("每日推荐").setIcon(R.drawable.a4));
        tab1.addTab(tab1.newTab().setText("飞花令").setIcon(R.drawable.a5));
        tab1.addTab(tab1.newTab().setText("诗歌社群").setIcon(R.drawable.a3));
        tab1.addTab(tab1.newTab().setText("排行榜").setIcon(R.drawable.a1));
        tab1.addTab(tab1.newTab().setText("会员专区").setIcon(R.drawable.a2));

        tab2.addTab(tab2.newTab().setText("首页").setIcon(R.drawable.b1));
        tab2.addTab(tab2.newTab().setText("写诗").setIcon(R.drawable.b2));
        tab2.addTab(tab2.newTab().setText("传图").setIcon(R.drawable.b3));
        tab2.addTab(tab2.newTab().setText("作品").setIcon(R.drawable.b4));
        tab2.addTab(tab2.newTab().setText("我的").setIcon(R.drawable.b5));
    }
}
