package com.example.day8_map;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.example.day8_map.Item.Cluster;
import com.example.day8_map.Item.ClusterItem;
import com.example.day8_map.Manager.ClusterManager;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * eSNYUvKLXADLNK7femE0nM6qrMfRplQa
 */
public class MainActivity extends AppCompatActivity implements BaiduMap.OnMapLoadedCallback {

    @BindView(R.id.mapView)
    MapView mMapView;
/*    @BindView(R.id.home_rb1)
    RadioButton homeRb1;
    @BindView(R.id.home_rb2)
    RadioButton homeRb2;
    @BindView(R.id.home_rb3)
    RadioButton homeRb3;
    @BindView(R.id.home_rb4)
    RadioButton homeRb4;*/
    private BaiduMap mBaiduMap;
    private LocationClient mLocationClient;
    private MapStatus mMapStatus;
    private ClusterManager<MyItem> mClusterManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mBaiduMap = mMapView.getMap();

        //开启地图的定位图层
        mBaiduMap.setMyLocationEnabled(true);
        initMap();
        //点聚合的设置
        initDian();
    }

    private void initDian() {
        mMapStatus = new MapStatus.Builder().target(new LatLng(39.914935, 116.403119)).zoom(8).build();
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setOnMapLoadedCallback(this);
        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(mMapStatus));

        // 定义点聚合管理类ClusterManager
        mClusterManager = new ClusterManager<>(this, mBaiduMap);
        // 添加Marker点
        addMarkers();
        // 设置地图监听，当地图状态发生改变时，进行点聚合运算
        mBaiduMap.setOnMapStatusChangeListener(mClusterManager);
        // 设置maker点击时的响应
        mBaiduMap.setOnMarkerClickListener(mClusterManager);

        mClusterManager.setOnClusterClickListener(new ClusterManager.OnClusterClickListener<MyItem>() {
            @Override
            public boolean onClusterClick(Cluster<MyItem> cluster) {
                Toast.makeText(MainActivity.this, "有" + cluster.getSize() + "个点", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        mClusterManager.setOnClusterItemClickListener(new ClusterManager.OnClusterItemClickListener<MyItem>() {
            @Override
            public boolean onClusterItemClick(MyItem item) {
                Toast.makeText(MainActivity.this, "点击单个Item", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

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
        MyLocationListener myLocationListener = new MyLocationListener();
        mLocationClient.registerLocationListener(myLocationListener);
//开启地图定位图层
        mLocationClient.start();
    }
    //显示定位的匿名内部类
    public class MyLocationListener extends BDAbstractLocationListener {
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
        mLocationClient.stop();
        mBaiduMap.setMyLocationEnabled(false);
        mMapView.onDestroy();
        mMapView = null;
    }
/*
    @OnCheckedChanged({R.id.home_rb1,R.id.home_rb2,R.id.home_rb3,R.id.home_rb4})
    public void  onCehcedChange(CompoundButton button, boolean isChecked) {
        switch (button.getId()){
            case R.id.home_rb1:
                //卫星图
                mMap.setMapType(BaiduMap.MAP_TYPE_NONE);
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

    }*/


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,0,0,"普通地图");
        menu.add(0,1,0,"卫星地图");
        menu.add(0,2,0,"空白地图");
        menu.add(0,3,0,"实时路况图");
        menu.add(0,4,0,"热力图");
        menu.add(0,5,0,"自定义路况图");
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case 0:
                //普通地图
                mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                break;
            case 1:
                //卫星地图
                mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
                break;
            case 2:
                //空白地图
                mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NONE);
                break;
            case 3:
                //开启交通图
                mBaiduMap.setTrafficEnabled(true);
                break;
            case 4:
                //开启热力图
                mBaiduMap.setBaiduHeatMapEnabled(true);
                break;
            case 5:
                mBaiduMap.setTrafficEnabled(true);
                mBaiduMap.setCustomTrafficColor("#ffba0101", "#fff33131", "#ffff9e19", "#00000000");
                //对地图状态做更新，否则可能不会触发渲染，造成样式定义无法立即生效。
                MapStatusUpdate u = MapStatusUpdateFactory.zoomTo(13);
                mBaiduMap.animateMapStatus(u);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * 向地图添加Marker点
     */
    public void addMarkers() {
        // 添加Marker点
        LatLng llA = new LatLng(39.963175, 116.400244);
        LatLng llB = new LatLng(39.942821, 116.369199);
        LatLng llC = new LatLng(39.939723, 116.425541);
        LatLng llD = new LatLng(39.906965, 116.401394);
        LatLng llE = new LatLng(39.956965, 116.331394);
        LatLng llF = new LatLng(39.886965, 116.441394);
        LatLng llG = new LatLng(39.996965, 116.411394);

        List<MyItem> items = new ArrayList<MyItem>();
        items.add(new MyItem(llA));
        items.add(new MyItem(llB));
        items.add(new MyItem(llC));
        items.add(new MyItem(llD));
        items.add(new MyItem(llE));
        items.add(new MyItem(llF));
        items.add(new MyItem(llG));

        mClusterManager.addItems(items);
    }

    /**
     * 每个Marker点，包含Marker点坐标以及图标
     */
    public class MyItem implements ClusterItem {
        private final LatLng mPosition;

        private MyItem(LatLng latLng) {
            mPosition = latLng;
        }

        @Override
        public LatLng getPosition() {
            return mPosition;
        }

        @Override
        public BitmapDescriptor getBitmapDescriptor() {
            return BitmapDescriptorFactory.fromResource(R.drawable.icon_gcoding);
        }
    }


    @Override
    public void onMapLoaded() {
        // TODO Auto-generated method stub
        mMapStatus = new MapStatus.Builder().zoom(9).build();
        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(mMapStatus));
    }

}