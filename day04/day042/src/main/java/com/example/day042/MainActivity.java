package com.example.day042;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day042.bean.JsonBean;
import com.example.day042.mvp.IView;
import com.example.day042.mvp.RelletM;
import com.example.day042.mvp.RelletP;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IView {

    private RecyclerView rcy;
    private TabItem tab1;
    private TabItem tab2;
    private TabItem tab3;
    private TabLayout tab;
    private Intent intent;
    private ArrayList<JsonBean.DataBean> list;
    private MyRcyAdapter myRcyAdapter;
    private RelletP relletP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        relletP = new RelletP(this);
        relletP.start();
    }

    private void initView() {
        rcy = (RecyclerView) findViewById(R.id.rcy);
        rcy.setLayoutManager(new GridLayoutManager(this,2));
        list = new ArrayList<>();
        myRcyAdapter = new MyRcyAdapter(list,this);
        rcy.setAdapter(myRcyAdapter);
        tab1 = (TabItem) findViewById(R.id.tab1);
        tab2 = (TabItem) findViewById(R.id.tab2);
        tab3 = (TabItem) findViewById(R.id.tab3);
        tab = (TabLayout) findViewById(R.id.tab);
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                intent = new Intent(MainActivity.this, Main2Activity.class);
                switch (tab.getPosition()) {

                    case 0:
                        intent.putExtra("id",0);
                        startActivity(intent);
                        break;
                    case 1:
                        intent.putExtra("id",1);
                        startActivity(intent);
                        break;
                    case 2:
                        intent.putExtra("id",2);
                        startActivity(intent);
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

    @Override
    public void showOk(Object object) {
        if (object instanceof JsonBean){
            JsonBean jsonBean= (JsonBean) object;
            List<JsonBean.DataBean> data = jsonBean.getData();
            list.addAll(data);
            myRcyAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showNo(String string) {

    }
}
