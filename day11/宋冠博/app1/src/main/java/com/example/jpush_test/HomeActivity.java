package com.example.jpush_test;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;

import com.google.android.material.tabs.TabLayout;

public class HomeActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private LinearLayout ll_a1;
    private LinearLayout ll_a2;
    private LinearLayout ll_a3;
    private LinearLayout ll_a4;
    private LinearLayout ll_a6;
    private NestedScrollView nl;
    private TabLayout tab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tab = (TabLayout) findViewById(R.id.tab);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        tab.addTab(tab.newTab().setText("首页").setIcon(R.drawable.a12));
        tab.addTab(tab.newTab().setText("布局").setIcon(R.drawable.a13));
        tab.addTab(tab.newTab().setText("我的").setIcon(R.drawable.a14));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        startActivity(new Intent(HomeActivity.this,MainActivity.class));
        return super.onOptionsItemSelected(item);
    }
}
