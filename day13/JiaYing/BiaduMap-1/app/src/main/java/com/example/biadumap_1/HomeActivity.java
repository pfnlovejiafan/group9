package com.example.biadumap_1;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.baidu.mapapi.map.MapView;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private RadioButton pt;
    private RadioButton wx;
    private RadioButton nullwhite;
    private RadioButton timemap;
    private RadioButton hot;
    private Button btn_clear;
    private RadioGroup rg;
    private EditText et_city;
    private EditText et_poi;
    private Button btn_search;
    private LinearLayout ll1;
    private MapView mMapView;
    private ListView poi_list;
    private RelativeLayout poi_detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.bmapView);
    }

    private void initView() {
        pt = (RadioButton) findViewById(R.id.pt);
        wx = (RadioButton) findViewById(R.id.wx);
        nullwhite = (RadioButton) findViewById(R.id.nullwhite);
        timemap = (RadioButton) findViewById(R.id.timemap);
        hot = (RadioButton) findViewById(R.id.hot);
        btn_clear = (Button) findViewById(R.id.btn_clear);
        rg = (RadioGroup) findViewById(R.id.rg);
        et_city = (EditText) findViewById(R.id.et_city);
        et_poi = (EditText) findViewById(R.id.et_poi);
        btn_search = (Button) findViewById(R.id.btn_search);
        ll1 = (LinearLayout) findViewById(R.id.ll1);
        mMapView = (MapView) findViewById(R.id.bmapView);
        poi_list = (ListView) findViewById(R.id.poi_list);
        poi_detail = (RelativeLayout) findViewById(R.id.poi_detail);

        btn_clear.setOnClickListener(this);
        btn_search.setOnClickListener(this);
        mMapView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_clear:

                break;
            case R.id.btn_search:

                break;
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }
}