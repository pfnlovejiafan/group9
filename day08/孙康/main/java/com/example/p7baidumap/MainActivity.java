package com.example.p7baidumap;

import android.Manifest;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.RouteLine;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRouteLine;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRoutePlanOption;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRoutePlanOption;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.github.dfqin.grantor.PermissionListener;
import com.github.dfqin.grantor.PermissionsUtil;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements BaiduMap.OnMapClickListener,
        OnGetRoutePlanResultListener {

    @BindView(R.id.map)
    MapView mMapView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.btn)
    Button btn;
    private BaiduMap mBaiduMap;
    private HashMap<String, Boolean> mapIs;
    /**
     * 该类提供一个能够显示和管理多个Overlay的基类
     * */
    private RouteLine route = null;

    boolean isFirstLoc = true;
    private LocationClient mLocationClient;
    public BDAbstractLocationListener myListener = new MyLocationListener();
    private RoutePlanSearch mInstance;
    private PopupWindow popupWindow;
    private EditText ev_start;
    private EditText ev_stop;
    private PlanNode start;
    private PlanNode stop;
    private PlanNode s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        registerForContextMenu(btn);
        init();
        show();

        mInstance = RoutePlanSearch.newInstance();
        mInstance.setOnGetRoutePlanResultListener(this);

        //动态权限
        PermissionsUtil.requestPermission(
                MainActivity.this,
                new PermissionListener() {
                    @Override
                    public void permissionGranted(@NonNull String[] permission) {
                        location();
                    }

                    @Override
                    public void permissionDenied(@NonNull String[] permission) {
                        Toast.makeText(MainActivity.this, "未得到授权", Toast.LENGTH_SHORT).show();
                    }
                },
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
        );
    }

    private void navPopupWindow() {
        //popupWindow弹窗
        View view = LayoutInflater.from(this).inflate(R.layout.layout_popu, null);
        Button btn_bu = view.findViewById(R.id.btn_bu);
        Button btn_qi = view.findViewById(R.id.btn_qi);
        Button btn_jia = view.findViewById(R.id.btn_jia);
        Button btn_gong = view.findViewById(R.id.btn_gong);
        ev_start = view.findViewById(R.id.ev_start);
        ev_stop = view.findViewById(R.id.ev_stop);
        popupWindow = new PopupWindow(view, -1, -2);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setAnimationStyle(R.style.PopAnimation);
        popupWindow.showAsDropDown(btn,0,0);


        btn_bu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getluxian();
                mInstance.walkingSearch((new WalkingRoutePlanOption())
                        .from(start)
                        .to(stop));

                popupWindow.dismiss();
            }
        });
        btn_qi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getluxian();

                popupWindow.dismiss();
            }
        });
        btn_jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getluxian();
                mInstance.drivingSearch((new DrivingRoutePlanOption())
                        .from(start)
                        .to(stop));
                popupWindow.dismiss();
            }
        });
        btn_gong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getluxian();
                mInstance.transitSearch((new TransitRoutePlanOption())
                        .from(start)
                        .city("北京")
                        .to(stop));
                popupWindow.dismiss();
            }
        });
    }

    private void getluxian() {
        start = PlanNode.withCityNameAndPlaceName("北京", ev_start.getText().toString());
        stop = PlanNode.withCityNameAndPlaceName("北京", ev_stop.getText().toString());



    }

    @OnClick({R.id.btn})
    public void click() {

        navPopupWindow();
    }


    private void location() {
        mBaiduMap.setMyLocationEnabled(true);
        //声明LocationClient类
        mLocationClient = new LocationClient(getApplicationContext());
        //注册监听函数
        mLocationClient.registerLocationListener(myListener);
        // 定位初始化
        mLocationClient = new LocationClient(this);
        isFirstLoc = true;
        // 设置定位的相关配置
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setOpenGps(true);//开启GPS
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

    private void show() {
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);

        //切换地图
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // 选中状态改变时被触发
                switch (item.getItemId()) {
                    case R.id.item_1:
                        Boolean map = mapIs.get("map");
                        if (map) {
                            mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
                            mapIs.put("map", false);
                        } else {
                            mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                            mapIs.put("map", true);
                        }
                        break;

                    case R.id.item_2:
                        Boolean map2 = mapIs.get("jiaotong");
                        if (map2) {
                            mBaiduMap.setTrafficEnabled(true);
                            mapIs.put("jiaotong", false);
                        } else {
                            mBaiduMap.setTrafficEnabled(false);
                            mapIs.put("jiaotong", true);
                        }
                        break;
                    case R.id.item_3:
                        Boolean map3 = mapIs.get("reli");
                        if (map3) {
                            //开启热力图
                            mBaiduMap.setBaiduHeatMapEnabled(true);
                            mapIs.put("reli", false);
                        } else {
                            //开启热力图
                            mBaiduMap.setBaiduHeatMapEnabled(false);
                            mapIs.put("reli", true);
                        }

                        break;
                    case R.id.item_4:

                        break;
                    case R.id.item_5:

                        break;
                }
                return false;
            }
        });
    }

    private void init() {
        mapIs = new HashMap<>();
        mapIs.put("map", true);
        mapIs.put("jiaotong", true);
        mapIs.put("reli", true);
        mapIs.put("dingwei", true);
        mapIs.put("shinei", true);

        mInstance = RoutePlanSearch.newInstance();
    }

    @Override
    public void onMapClick(LatLng latLng) {
        s = PlanNode.withLocation(latLng);

//        PlanNode e = PlanNode.withLocation(densitylatLng);
    }

    @Override
    public void onMapPoiClick(MapPoi mapPoi) {

    }

    @Override
    public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {

    }

    @Override
    public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {

    }

    @Override
    public void onGetMassTransitRouteResult(MassTransitRouteResult massTransitRouteResult) {

    }

    @Override
    public void onGetDrivingRouteResult(DrivingRouteResult result) {
        if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
            Toast.makeText(MainActivity.this, "抱歉，未找到结果", Toast.LENGTH_SHORT).show();
        }
        if (result.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
            // 起终点或途经点地址有岐义，通过以下接口获取建议查询信息
            return;
        }
        if (result.error == SearchResult.ERRORNO.NO_ERROR) {
            if (result.getRouteLines().size() > 0) {
                DrivingRouteLine route = result.getRouteLines().get(0);
                int distance = route.getDistance();
                Toast.makeText(this, "距离" + distance + "m", Toast.LENGTH_SHORT).show();

//                //可自定义后面的DrivingRouteOverlay以改变起点终点图标，路径颜色，和点击事件等
//                DrivingRouteOverlay overlay = new DrivingRouteOverlay(mBaiduMap);
//                ///mBaiduMap.setOnMarkerClickListener(overlay);
//                overlay.setData(route);
//                overlay.addToMap();
//                overlay.zoomToSpan();
            } else {
                Toast.makeText(MainActivity.this, "抱歉，未找到结果", Toast.LENGTH_SHORT).show();
                return;
            }
        }
    }

    @Override
    public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {

    }

    @Override
    public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {

    }

    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //mapView 销毁后不在处理新接收的位置
            if (location == null || mMapView == null) {
                return;
            }
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(location.getDirection()).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);

            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng xy = new LatLng(location.getLatitude(), location.getLongitude());
                MapStatusUpdate status = MapStatusUpdateFactory.newLatLng(xy);
                mBaiduMap.animateMapStatus(status);
            }
        }
    }


    @Override
    protected void onResume() {
        mMapView.onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mLocationClient.stop();
        mBaiduMap.setMyLocationEnabled(false);
        mMapView.onDestroy();
        mMapView = null;
        super.onDestroy();
    }

}