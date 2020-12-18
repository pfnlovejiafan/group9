package com.example.skp7day04zuoye;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.skp7day04zuoye.view.fragment.AddFragment;
import com.example.skp7day04zuoye.view.fragment.FindFragment;
import com.example.skp7day04zuoye.view.fragment.HomeFragment;
import com.example.skp7day04zuoye.view.fragment.MyFragment;
import com.example.skp7day04zuoye.view.fragment.ShoppingFragment;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.frame)
    FrameLayout frame;
    @BindView(R.id.tab)
    TabLayout tab;
    private HomeFragment homeFragment;
    private FindFragment findFragment;
    private AddFragment addFragment;
    private ShoppingFragment shoppingFragment;
    private MyFragment myFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        homeFragment = new HomeFragment();
        findFragment = new FindFragment();
        addFragment = new AddFragment();
        shoppingFragment = new ShoppingFragment();
        myFragment = new MyFragment();
        tab.addTab(tab.newTab().setText("首页").setIcon(R.drawable.home));
        tab.addTab(tab.newTab().setText("发现").setIcon(R.drawable.find));
        tab.addTab(tab.newTab().setIcon(R.drawable.add1));
        tab.addTab(tab.newTab().setText("商城").setIcon(R.drawable.shop));
        tab.addTab(tab.newTab().setText("我的").setIcon(R.drawable.my));

        getSupportFragmentManager().beginTransaction().replace(R.id.frame,homeFragment).commit();


        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame,homeFragment).commit();
                        break;

                    case 1:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame,findFragment).commit();
                        break;

                    case 2:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame,addFragment).commit();
                        break;

                    case 3:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame,shoppingFragment).commit();
                        break;

                    case 4:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame,myFragment).commit();
                        break;
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