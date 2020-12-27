package com.example.mvp.mvp.ui.activity;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMapOptions;
import com.baidu.mapapi.map.MapView;
import com.example.mvp.R;
import com.github.dfqin.grantor.PermissionListener;
import com.github.dfqin.grantor.PermissionsUtil;

import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.Unbinder;

public class BaiduMapActivity extends AppCompatActivity {
    private MapView mMapView = null;
    private RadioButton home_rb1;
    private RadioButton home_rb2;
    private RadioButton home_rb3;
    private RadioButton home_rb4;
    private BaiduMap mMap;
    private Unbinder mBind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_map_activity);
        mBind = ButterKnife.bind(this);
        initView();
        initPermission();
        //获取地图控件引用
        mMapView = findViewById(R.id.bmapView);
        mMap = mMapView.getMap();

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
        mBind.unbind();
    }


    private void initPermission() {
        PermissionsUtil.requestPermission(this, new PermissionListener() {
            @Override
            public void permissionGranted(@NonNull String[] permission) {

            }

            @Override
            public void permissionDenied(@NonNull String[] permission) {

            }
        }, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE});

    }

    private void initView() {
        home_rb1 = (RadioButton) findViewById(R.id.home_rb1);
        home_rb2 = (RadioButton) findViewById(R.id.home_rb2);
        home_rb3 = (RadioButton) findViewById(R.id.home_rb3);
        home_rb4 = (RadioButton) findViewById(R.id.home_rb4);

/*        //兰姆达表达式
        home_rb4.setOnCheckedChangeListener(((buttonView, isChecked) -> {

        }));*/
    }

    @OnCheckedChanged({R.id.home_rb1, R.id.home_rb2, R.id.home_rb3, R.id.home_rb4})
    public void onCehcedChange(CompoundButton button, boolean isChecked) {
        switch (button.getId()) {
            case R.id.home_rb1:
                //卫星图
                mMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
                break;
            case R.id.home_rb2:
                //普通地图
                mMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                break;
            case R.id.home_rb3:
                //开启热力图
                mMap.setBaiduHeatMapEnabled(true);
                break;
            case R.id.home_rb4:
                //开启实时交通图
                mMap.setTrafficEnabled(true);
                break;
        }

    }


}