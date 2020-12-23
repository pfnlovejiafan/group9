package com.example.day8_baidumap1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;

import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;

import com.github.dfqin.grantor.PermissionListener;
import com.github.dfqin.grantor.PermissionsUtil;

/**
 *  Manifest.permission.ACCESS_COARSE_LOCATION,
 *                 Manifest.permission.ACCESS_FINE_LOCATION
 */
public class MainActivity extends AppCompatActivity {
    protected MapView mMapView;
    public BaiduMap mBaiduMap;
    private LocationClient mLocationClient;
    private MyLocationListener myLocationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取地图控件引用
         mMapView = (MapView) findViewById(R.id.bmapView);
         initPermission();
         mBaiduMap = mMapView.getMap();
         mBaiduMap.setMyLocationEnabled(true);

         initMap();
    }


    private void initMap() {
        //定位初始化
        mLocationClient = new LocationClient(this);
        //通过LocationClientOption设置LocationClient相关参数
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);

        //设置locationClientOption
        mLocationClient.setLocOption(option);

        //注册LocationListener监听器
        myLocationListener = new MyLocationListener();
        mLocationClient.registerLocationListener(myLocationListener);

        //开启地图定位图层
        mLocationClient.start();
    }

    public   class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //mapView 销毁后不在处理新接收的位置
            if (location == null || mMapView == null){
                return;
            }
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(location.getDirection()).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);
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

    private void initPermission() {
        PermissionsUtil.requestPermission(this, new PermissionListener() {
            @Override
            public void permissionGranted(@NonNull String[] permission) {
            }

            @Override
            public void permissionDenied(@NonNull String[] permission) {

            }
        }, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
               });
    }


    /*切换地图类型*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,0,0,"普通地图");
        menu.add(0,1,0,"卫星地图");
        menu.add(0,2,0,"交通地图");
        menu.add(0,3,0,"热力图");
        menu.add(0,4,0,"空白图");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case 0:
                mBaiduMap = mMapView.getMap();
            //普通地图 ,mBaiduMap是地图控制器对象
                mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);

                break;
            case 1:
             //卫星地图
                BaiduMap map = mMapView.getMap();
                map.setMapType(BaiduMap.MAP_TYPE_SATELLITE);

                break;
            case 2:
                BaiduMap map1 = mMapView.getMap();
                //设置实时路况图
                map1.setTrafficEnabled(true);
                break;
            case 3:
                BaiduMap map3 = mMapView.getMap();
            //开启热力图
                map3.setBaiduHeatMapEnabled(true);

                break;
            case 4:
                BaiduMap map2 = mMapView.getMap();
                //空白地图
                map2.setMapType(BaiduMap.MAP_TYPE_NONE);


                break;
        }
        return super.onOptionsItemSelected(item);
    }


}