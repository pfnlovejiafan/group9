package com.example.skp7day03zuoye;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.skp7day03zuoye.view.fragment.FindFragment;
import com.example.skp7day03zuoye.view.fragment.HomeFragment;
import com.example.skp7day03zuoye.view.fragment.MyFragment;
import com.example.skp7day03zuoye.view.fragment.PublishFragment;
import com.example.skp7day03zuoye.view.fragment.SofaFragment;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.frame)
    FrameLayout frame;
    @BindView(R.id.tab)
    TabLayout tab;
    private HomeFragment homeFragment;
    private SofaFragment sofaFragment;
    private FindFragment findFragment;
    private MyFragment myFragment;
    private PublishFragment publishFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {


        homeFragment = new HomeFragment();
        sofaFragment = new SofaFragment();
        publishFragment = new PublishFragment();
        findFragment = new FindFragment();
        myFragment = new MyFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame,sofaFragment).commit();

        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frame,homeFragment).commit();
                        break;
                    case 1:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frame,sofaFragment).commit();
                        break;
                    case 2:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frame,publishFragment).commit();
                        break;
                    case 3:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frame,findFragment).commit();
                        break;
                    case 4:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frame,myFragment).commit();
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