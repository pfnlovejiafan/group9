package com.example.ceshi1.mvp.mvp.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRoutePlanOption;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.example.ceshi1.R;
import com.example.ceshi1.mvp.mapapi.clusterutil.clustering.ClusterItem;
import com.example.ceshi1.mvp.mapapi.clusterutil.clustering.ClusterManager;
import com.example.ceshi1.mvp.mapapi.overlayutil.PoiOverlay;
import com.example.ceshi1.mvp.mapapi.overlayutil.WalkingRouteOverlay;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_pt)
    Button btnPt;
    @BindView(R.id.btn_wx)
    Button btnWx;
    @BindView(R.id.bmapView)
    MapView bmapView;
    @BindView(R.id.et_one)
    EditText etOne;
    @BindView(R.id.et_two)
    EditText etTwo;
    @BindView(R.id.btn_one)
    Button btnOne;
    @BindView(R.id.et1)
    EditText et1;
    @BindView(R.id.et2)
    EditText et2;
    @BindView(R.id.et3)
    EditText et3;
    @BindView(R.id.et4)
    EditText et4;
    @BindView(R.id.btn_two)
    Button btnTwo;

    private BaiduMap map;
    private LocationClient mLocationClient;
    private PoiSearch mPoiSearch;
    private RoutePlanSearch mSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        map = bmapView.getMap();
        map.setMyLocationEnabled(true);


        initMap();
        //initApp();
        initHa();
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

    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //mapView 销毁后不在处理新接收的位置
            if (location == null || map == null) {
                return;
            }
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(location.getDirection()).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            map.setMyLocationData(locData);
        }
    }

    @OnClick({R.id.btn_pt, R.id.btn_wx, R.id.btn_one,R.id.btn_two})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_pt:
                map.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                break;
            case R.id.btn_wx:
                map.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
                break;
            case R.id.btn_one:
                String city = etOne.getText().toString();
                String keyword = etTwo.getText().toString();

                mPoiSearch.searchInCity(new PoiCitySearchOption()
                        .city(city) //必填
                        .keyword(keyword) //必填
                        .pageNum(10));
                break;
            case R.id.btn_two:
                map.clear();
                String s1 = et1.getText().toString();
                String s2 = et2.getText().toString();
                String s3 = et3.getText().toString();
                String s4 = et4.getText().toString();

                PlanNode stNode = PlanNode.withCityNameAndPlaceName(s1, s2);
                PlanNode enNode = PlanNode.withCityNameAndPlaceName(s3, s4);

                mSearch.walkingSearch((new WalkingRoutePlanOption())
                        .from(stNode)
                        .to(enNode));
                break;
        }
    }

    private void initHa() {
        mSearch = RoutePlanSearch.newInstance();

        mSearch.setOnGetRoutePlanResultListener(listener);
    }

    private void initApp() {
        //mPoiSearch = PoiSearch.newInstance();
        //mPoiSearch.setOnGetPoiSearchResultListener(listener);
    }

    OnGetRoutePlanResultListener listener = new OnGetRoutePlanResultListener() {
        @Override
        public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {
            //创建WalkingRouteOverlay实例
            WalkingRouteOverlay overlay = new WalkingRouteOverlay(map);
            if (walkingRouteResult.getRouteLines().size() > 0) {
                //获取路径规划数据,(以返回的第一条数据为例)
                //为WalkingRouteOverlay实例设置路径数据
                overlay.setData(walkingRouteResult.getRouteLines().get(0));
                //在地图上绘制WalkingRouteOverlay
                overlay.addToMap();
            }
        }

        @Override
        public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {

        }

        @Override
        public void onGetMassTransitRouteResult(MassTransitRouteResult massTransitRouteResult) {

        }

        @Override
        public void onGetDrivingRouteResult(DrivingRouteResult drivingRouteResult) {

        }

        @Override
        public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {

        }

        @Override
        public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {

        }
    };

//    OnGetPoiSearchResultListener listener = new OnGetPoiSearchResultListener() {
//            @Override
//            public void onGetPoiResult(PoiResult poiResult) {
//                if (poiResult.error == SearchResult.ERRORNO.NO_ERROR) {
//                    map.clear();
//
//                    //创建PoiOverlay对象
//                    PoiOverlay poiOverlay = new PoiOverlay(map);
//
//                    //设置Poi检索数据
//                    poiOverlay.setData(poiResult);
//
//                    //将poiOverlay添加至地图并缩放至合适级别
//                    poiOverlay.addToMap();
//                    poiOverlay.zoomToSpan();
//                }
//            }
//
//        @Override
//        public void onGetPoiDetailResult(PoiDetailSearchResult poiDetailSearchResult) {
//
//        }
//
//        @Override
//        public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {
//
//        }
//
//        //废弃
//        @Override
//        public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {
//
//        }
//    };

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        bmapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        bmapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mPoiSearch.destroy();
        mSearch.destroy();
        bmapView.onDestroy();
    }
}
