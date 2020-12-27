package com.example.mybaidumap;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.baidu.bikenavi_demo.BNaviGuideActivity;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.bikenavi.BikeNavigateHelper;
import com.baidu.mapapi.bikenavi.adapter.IBEngineInitListener;
import com.baidu.mapapi.bikenavi.adapter.IBRoutePlanListener;
import com.baidu.mapapi.bikenavi.model.BikeRoutePlanError;
import com.baidu.mapapi.bikenavi.params.BikeNaviLaunchParam;
import com.baidu.mapapi.bikenavi.params.BikeRouteNodeInfo;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.overlayutil.BikingRouteOverlay;
import com.baidu.mapapi.overlayutil.DrivingRouteOverlay;
import com.baidu.mapapi.overlayutil.MassTransitRouteOverlay;
import com.baidu.mapapi.overlayutil.PoiOverlay;
import com.baidu.mapapi.overlayutil.WalkingRouteOverlay;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchOption;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.route.BikingRoutePlanOption;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRoutePlanOption;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRoutePlanOption;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.baidu.mapapi.search.sug.OnGetSuggestionResultListener;
import com.baidu.mapapi.search.sug.SuggestionResult;
import com.baidu.mapapi.search.sug.SuggestionSearch;
import com.baidu.mapapi.search.sug.SuggestionSearchOption;
import com.github.dfqin.grantor.PermissionListener;
import com.github.dfqin.grantor.PermissionsUtil;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.map_view)
    MapView mMapView;
    @BindView(R.id.image_loc)
    Button imageLoc;
    @BindView(R.id.btn_route)
    Button btnRoute;
    @BindView(R.id.btn_nav)
    Button btnNav;
    private BaiduMap mBaiduMap = null;
    private Context context;

    //定位相关
    private double mLatitude;
    private double mLongtitude;

    //方向传感器
    private MyOrientationListener mMyOrientationListener;
    private float mCurrentX;
    //自定义图标
    private LocationClient mLocationClient;
    public BDAbstractLocationListener myListener;
    private LatLng mLastLocationData;
    private boolean isFirstin = true;
    private RoutePlanSearch mSearch;
    private EditText ev_start_chengshi;
    private EditText ev_start_weizhi;
    private EditText ev_stop_chengshi;
    private EditText ev_stop_weizhi;
    private PlanNode stNode;
    private PlanNode enNode;
    private WalkingRouteOverlay mWalking;
    private MassTransitRouteOverlay mMassTran;
    private DrivingRouteOverlay mDriving;
    private PoiSearch mPoiSearch;
    private PoiResult mPoiResult;
    private PoiOverlay poiOverlay;
    private String startWeizhi;
    private SuggestionSearch suggestionSearch;
    private RecyclerView rv;
    private EditText ev_nav;
    private String sages;
    private double mNavLatitude;
    private double mNavLongitude;
    private BikeNaviLaunchParam param;
    private BikeNavigateHelper mNaviHelper;
    private Marker mStartMarker;
    private BikeNaviLaunchParam bikeParam;
    private LatLng endPt;

    private BitmapDescriptor bdStart = BitmapDescriptorFactory
            .fromResource(R.drawable.icon_start);
    private BitmapDescriptor bdEnd = BitmapDescriptorFactory
            .fromResource(R.drawable.icon_end);
    private Marker mEndMarker;
    private LatLng startPt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        this.context = this;
        init();



    }

    private void init() {
        //获取地图控件引用
        mBaiduMap = mMapView.getMap();

        //路线规划
        mSearch = RoutePlanSearch.newInstance();
        mSearch.setOnGetRoutePlanResultListener(listener);

        //POI
        mPoiSearch = PoiSearch.newInstance();
        mPoiSearch.setOnGetPoiSearchResultListener(poiSearch);

        //输入检索
        suggestionSearch = SuggestionSearch.newInstance();
        suggestionSearch.setOnGetSuggestionResultListener(Suggestion);

        //动态权限
        PermissionsUtil.requestPermission(
                MainActivity.this,
                new PermissionListener() {
                    @Override
                    public void permissionGranted(@NonNull String[] permission) {
                        //我的位置
                        initMyLocation();
                        //初始化导航
//                        initNav();
                    }

                    @Override
                    public void permissionDenied(@NonNull String[] permission) {
                        Toast.makeText(MainActivity.this, "未授权无法拨打", Toast.LENGTH_SHORT).show();
                    }
                },
//                Manifest.permission.RECORD_AUDIO,
//                Manifest.permission.ACCESS_NETWORK_STATE,
//                Manifest.permission.INTERNET,
//                Manifest.permission.READ_PHONE_STATE,
//                Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
//                Manifest.permission.MODIFY_AUDIO_SETTINGS,
//                Manifest.permission.WRITE_SETTINGS,
//                Manifest.permission.ACCESS_WIFI_STATE,
//                Manifest.permission.CHANGE_WIFI_STATE,
//                Manifest.permission.CHANGE_WIFI_MULTICAST_STATE
        );

    }


    //TODO  初始化导航
//    private void initNav() {
//        MapStatus.Builder builder = new MapStatus.Builder();
//        builder.target(new LatLng(40.048424, 116.313513)).zoom(15);
//        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
//
//        mNaviHelper = BikeNavigateHelper.getInstance();
//        startPt = new LatLng(40.057038, 116.307899);
//        endPt = new LatLng(40.035916, 116.340722);
//        /*构造导航起终点参数对象*/
//        BikeRouteNodeInfo bikeStartNode = new BikeRouteNodeInfo();
//        bikeStartNode.setLocation(startPt);
//        BikeRouteNodeInfo bikeEndNode = new BikeRouteNodeInfo();
//        bikeEndNode.setLocation(endPt);
//
//        bikeParam = new BikeNaviLaunchParam().startNodeInfo(bikeStartNode).endNodeInfo(bikeEndNode);
//        MarkerOptions ooA = new MarkerOptions().position(startPt).icon(bdStart)
//                .zIndex(9).draggable(true);
//
//        mStartMarker = (Marker) (mBaiduMap.addOverlay(ooA));
//        mStartMarker.setDraggable(true);
//        MarkerOptions ooB = new MarkerOptions().position(endPt).icon(bdEnd)
//                .zIndex(5);
//        mEndMarker = (Marker) (mBaiduMap.addOverlay(ooB));
//        mEndMarker.setDraggable(true);
//
//        mBaiduMap.setOnMarkerDragListener(new BaiduMap.OnMarkerDragListener() {
//            public void onMarkerDrag(Marker marker) {
//            }
//            public void onMarkerDragEnd(Marker marker) {
//                if(marker == mStartMarker){
//                    startPt = marker.getPosition();
//                }else if(marker == mEndMarker){
//                    endPt = marker.getPosition();
//                }
//                BikeRouteNodeInfo bikeStartNode = new BikeRouteNodeInfo();
//                bikeStartNode.setLocation(startPt);
//                BikeRouteNodeInfo bikeEndNode = new BikeRouteNodeInfo();
//                bikeEndNode.setLocation(endPt);
//                bikeParam = new BikeNaviLaunchParam().startNodeInfo(bikeStartNode).endNodeInfo(bikeEndNode);
//            }
//
//            public void onMarkerDragStart(Marker marker) {
//            }
//        });
//    }


    @OnClick({R.id.image_loc, R.id.btn_route, R.id.btn_nav})
    public void onClick(View v) {
        SDKInitializer.initialize(getApplicationContext());
        switch (v.getId()) {
            //回到我的位置
            case R.id.image_loc:
                centerToMyLocation(mLatitude, mLongtitude);
                break;
            case R.id.btn_route:
                Route();
                break;
            case R.id.btn_nav:
//                getKey();
                break;
        }
    }

//    private void getKey() {
//        View view = LayoutInflater.from(context).inflate(R.layout.ev_item, null);
//        ev_nav = view.findViewById(R.id.ev_nav);
//        rv = view.findViewById(R.id.rv);
//
//        //TODO 输入检测
//        ev_nav.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                suggestionSearch.requestSuggestion(new SuggestionSearchOption()
//                        .city("北京")
//                        .keyword(s + ""));
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//        new AlertDialog.Builder(context).setTitle("请输入目的地:")
//                .setView(view)
//                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        sages = ev_nav.getText().toString().trim();
//                        startBikeNav();
//                    }
//                })
//                .setNegativeButton("取消", null)
//                .show();
//    }

    //TODO  开启导航
//    private void startBikeNav() {
//        try {
//            BikeNavigateHelper.getInstance().initNaviEngine(this, new IBEngineInitListener() {
//                @Override
//                public void engineInitSuccess() {
//                    Log.e("TAG", "BikeNavi engineInitSuccess");
//                    routePlanWithBikeParam();
//                }
//
//                @Override
//                public void engineInitFail() {
//                    Log.e("TAG", "BikeNavi engineInitFail");
//                    BikeNavigateHelper.getInstance().unInitNaviEngine();
//                }
//            });
//        } catch (Exception e) {
//            Log.e("TAG", "startBikeNavi Exception");
//            e.printStackTrace();
//        }
//    }
//    private void routePlanWithBikeParam() {
//        BikeNavigateHelper.getInstance().routePlanWithRouteNode(bikeParam, new IBRoutePlanListener() {
//            @Override
//            public void onRoutePlanStart() {
//                Log.e("TAG", "BikeNavi onRoutePlanStart");
//            }
//
//            @Override
//            public void onRoutePlanSuccess() {
//                Log.e("TAG", "BikeNavi onRoutePlanSuccess");
//                Intent intent = new Intent();
//                intent.setClass(context, BNaviGuideActivity.class);
//                startActivity(intent);
//            }
//
//            @Override
//            public void onRoutePlanFail(BikeRoutePlanError error) {
//                Log.e("TAG", "BikeNavi onRoutePlanFail");
//            }
//
//        });
//    }

    //TODO 路线规划
    private void Route() {
        //popupWindow弹窗
        View view = LayoutInflater.from(context).inflate(R.layout.route_popu, null);
        Button btn_buxing = view.findViewById(R.id.btn_buxing);
        Button btn_qixing = view.findViewById(R.id.btn_qixing);
        Button btn_jiache = view.findViewById(R.id.btn_jiache);
        Button btn_gongjiao = view.findViewById(R.id.btn_gongjiao);
        ev_start_chengshi = view.findViewById(R.id.ev_start_chengshi);
        ev_start_weizhi = view.findViewById(R.id.ev_start_weizhi);
        ev_stop_chengshi = view.findViewById(R.id.ev_stop_chengshi);
        ev_stop_weizhi = view.findViewById(R.id.ev_stop_weizhi);

        PopupWindow popupWindow = new PopupWindow(view, -1, -2);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.showAtLocation(mMapView, Gravity.CENTER, 0, 0);

        btn_buxing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getStr();
                mBaiduMap = mMapView.getMap();
                MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(15.0f);
                mBaiduMap.setMapStatus(msu);
                Address address = null;
                try {
                    address = new Geocoder(context, Locale.CHINA).getFromLocationName(startWeizhi, 1).get(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                centerToMyLocation(address.getLatitude(), address.getLongitude());
                mSearch.walkingSearch((new WalkingRoutePlanOption())
                        .from(stNode)
                        .to(enNode));
                popupWindow.dismiss();
            }
        });

        btn_qixing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getStr();
                mBaiduMap = mMapView.getMap();
                MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(15.0f);
                mBaiduMap.setMapStatus(msu);
                Address address = null;
                try {
                    address = new Geocoder(context, Locale.CHINA).getFromLocationName(startWeizhi, 1).get(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                centerToMyLocation(address.getLatitude(), address.getLongitude());
                mSearch.bikingSearch((new BikingRoutePlanOption())
                        .from(stNode)
                        .to(enNode));
                popupWindow.dismiss();
            }
        });

        btn_jiache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getStr();
                mBaiduMap = mMapView.getMap();
                MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(15.0f);
                mBaiduMap.setMapStatus(msu);
                Address address = null;
                try {
                    address = new Geocoder(context, Locale.CHINA).getFromLocationName(startWeizhi, 1).get(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                centerToMyLocation(address.getLatitude(), address.getLongitude());
                mSearch.drivingSearch((new DrivingRoutePlanOption())
                        .from(stNode)
                        .to(enNode));
                popupWindow.dismiss();
            }
        });

        btn_gongjiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getStr();
                mBaiduMap = mMapView.getMap();
                MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(15.0f);
                mBaiduMap.setMapStatus(msu);
                Address address = null;
                try {
                    address = new Geocoder(context, Locale.CHINA).getFromLocationName(startWeizhi, 1).get(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                centerToMyLocation(address.getLatitude(), address.getLongitude());
                mSearch.masstransitSearch((new MassTransitRoutePlanOption())
                        .from(stNode)
                        .to(enNode));
                popupWindow.dismiss();
            }
        });

    }

    //TODO 获取路线信息
    private void getStr() {
        String startChengshi = ev_start_chengshi.getText().toString().trim();
        startWeizhi = ev_start_weizhi.getText().toString().trim();
        String stopChengshi = ev_stop_chengshi.getText().toString().trim();
        String stopWeizhi = ev_stop_weizhi.getText().toString().trim();

        stNode = PlanNode.withCityNameAndPlaceName(startChengshi, startWeizhi);
        enNode = PlanNode.withCityNameAndPlaceName(stopChengshi, stopWeizhi);
    }

    //TODO 初始化定位
    private void initMyLocation() {
        //缩放地图
        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(15.0f);
        mBaiduMap.setMapStatus(msu);
        //开启定位
        mBaiduMap.setMyLocationEnabled(true);
        //声明LocationClient类
        mLocationClient = new LocationClient(this);
        //通过LocationClientOption设置LocationClient相关参数
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setIsNeedAddress(true);//设置是否需要地址信息
        option.setScanSpan(1000);
        //设置locationClientOption
        mLocationClient.setLocOption(option);
        myListener = new MyLocationListener();
        //监听函数
        mLocationClient.registerLocationListener(myListener);
        //初始化图标
        // mIconLocation = BitmapDescriptorFactory.fromResource(R.drawable.navi_map_gps);
        initOrientation();
        //开始定位
        mLocationClient.start();
    }

    //TODO 定位
    private class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //mapView 销毁后不在处理新接收的位置
            if (location == null || mMapView == null) {
                return;
            }
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(mCurrentX).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);
            //设置自定义图标
            MyLocationConfiguration config = new
                    MyLocationConfiguration(
                    MyLocationConfiguration.LocationMode.NORMAL, true, null);
            mBaiduMap.setMyLocationConfiguration(config);
            //更新经纬度
            mLatitude = location.getLatitude();
            mLongtitude = location.getLongitude();
            //设置起点
            mLastLocationData = new LatLng(mLatitude, mLongtitude);
            if (isFirstin) {
                centerToMyLocation(location.getLatitude(), location.getLongitude());

                if (location.getLocType() == BDLocation.TypeGpsLocation) {
                    // GPS定位结果
                    Toast.makeText(context, "定位:" + location.getAddrStr(), Toast.LENGTH_SHORT).show();
                } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
                    // 网络定位结果
                    Toast.makeText(context, "定位:" + location.getAddrStr(), Toast.LENGTH_SHORT).show();
                } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {
                    // 离线定位结果
                    Toast.makeText(context, "定位:" + location.getAddrStr(), Toast.LENGTH_SHORT).show();
                } else if (location.getLocType() == BDLocation.TypeServerError) {
                    Toast.makeText(context, "定位:服务器错误", Toast.LENGTH_SHORT).show();
                } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                    Toast.makeText(context, "定位:网络错误", Toast.LENGTH_SHORT).show();
                } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                    Toast.makeText(context, "定位:手机模式错误，请检查是否飞行", Toast.LENGTH_SHORT).show();
                }
                isFirstin = false;
            }
        }
    }

    //TODO 路线规划
    OnGetRoutePlanResultListener listener = new OnGetRoutePlanResultListener() {
        @Override
        public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {
            //创建WalkingRouteOverlay实例
            mWalking = new WalkingRouteOverlay(mBaiduMap);
            if (walkingRouteResult.getRouteLines() != null && walkingRouteResult.getRouteLines().size() > 0) {
                //获取路径规划数据,(以返回的第一条数据为例)
                //为WalkingRouteOverlay实例设置路径数据
                mWalking.setData(walkingRouteResult.getRouteLines().get(0));
                //在地图上绘制WalkingRouteOverlay
                mWalking.addToMap();
            } else {
                Toast.makeText(context, "没有该路线", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {
        }

        @Override
        public void onGetMassTransitRouteResult(MassTransitRouteResult massTransitRouteResult) {
            mMassTran = new MassTransitRouteOverlay(mBaiduMap);
            if (massTransitRouteResult.getRouteLines() != null && massTransitRouteResult.getRouteLines().size() > 0) {
                //获取路线规划数据（以返回的第一条数据为例）
                //为MassTransitRouteOverlay设置数据
                mMassTran.setData(massTransitRouteResult.getRouteLines().get(0));
                //在地图上绘制Overlay
                mMassTran.addToMap();
            } else {
                Toast.makeText(context, "没有该路线", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onGetDrivingRouteResult(DrivingRouteResult drivingRouteResult) {
            mDriving = new DrivingRouteOverlay(mBaiduMap);
            if (drivingRouteResult.getRouteLines() != null && drivingRouteResult.getRouteLines().size() > 0) {
                //获取路径规划数据,(以返回的第一条路线为例）
                //为DrivingRouteOverlay实例设置数据
                mDriving.setData(drivingRouteResult.getRouteLines().get(0));
                //在地图上绘制DrivingRouteOverlay
                mDriving.addToMap();
            } else {
                Toast.makeText(context, "没有该路线", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {
        }

        @Override
        public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {
            //创建BikingRouteOverlay实例
            BikingRouteOverlay overlay = new BikingRouteOverlay(mBaiduMap);
            if (bikingRouteResult.getRouteLines() != null && bikingRouteResult.getRouteLines().size() > 0) {
                //获取路径规划数据,(以返回的第一条路线为例）
                //为BikingRouteOverlay实例设置数据
                overlay.setData(bikingRouteResult.getRouteLines().get(0));
                //在地图上绘制BikingRouteOverlay
                overlay.addToMap();
            } else {
                Toast.makeText(context, "没有该路线", Toast.LENGTH_SHORT).show();
            }
        }
    };

    //TODO 输入检索监听
    OnGetSuggestionResultListener Suggestion = new OnGetSuggestionResultListener() {
        @Override
        public void onGetSuggestionResult(SuggestionResult suggestionResult) {
            //处理sug检索结果
            List<SuggestionResult.SuggestionInfo> allSuggestions = suggestionResult.getAllSuggestions();
            if (allSuggestions != null && allSuggestions.size() > 0) {
                rv.setLayoutManager(new LinearLayoutManager(context));
                rv.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
                HomeActRlAdapter actRlAdapter = new HomeActRlAdapter(context, allSuggestions);
                rv.setAdapter(actRlAdapter);
                actRlAdapter.setOnClickItem(new HomeActRlAdapter.OnClickItem() {
                    @Override
                    public void onClick(int position) {
                        SuggestionResult.SuggestionInfo suggestionInfo = allSuggestions.get(position);
                        ev_nav.setText(suggestionInfo.key);
                    }
                });

            }
        }
    };

    //TODO 检索监听
    OnGetPoiSearchResultListener poiSearch = new OnGetPoiSearchResultListener() {
        @Override
        public void onGetPoiResult(PoiResult poiResult) {
            mPoiResult = poiResult;
            if (poiResult.error == SearchResult.ERRORNO.NO_ERROR) {
                mBaiduMap.clear();

                //创建PoiOverlay对象
                poiOverlay = new PoiOverlay(mBaiduMap);

                //设置Poi检索数据
                poiOverlay.setData(poiResult);

                //将poiOverlay添加至地图并缩放至合适级别
                poiOverlay.addToMap();
                poiOverlay.zoomToSpan();
            }

        }

        @Override
        public void onGetPoiDetailResult(PoiDetailSearchResult poiDetailSearchResult) {
            //PoiInfo 检索到的第一条信息
            PoiInfo poi = mPoiResult.getAllPoi().get(0);
            //通过第一条检索信息对应的uid发起详细信息检索
            mPoiSearch.searchPoiDetail((new PoiDetailSearchOption())
                    .poiUids(poi.uid)); // uid的集合，最多可以传入10个uid，多个uid之间用英文逗号分隔。
        }

        @Override
        public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

        }

        //废弃
        @Override
        public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

        }
    };

    //TODO 回到定位中心
    private void centerToMyLocation(double latitude, double longitude) {
        mBaiduMap.clear();
        mLastLocationData = new LatLng(latitude, longitude);
        MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(mLastLocationData);
        mBaiduMap.animateMapStatus(msu);
    }

    //TODO 传感器
    private void initOrientation() {
        mMyOrientationListener = new MyOrientationListener(context);
        mMyOrientationListener.setOnOrientationListener(new MyOrientationListener.OnOrientationListener() {
            @Override
            public void onOrientationChanged(float x) {
                mCurrentX = x;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        //开启定位
        mBaiduMap.setMyLocationEnabled(true);
        if (!mLocationClient.isStarted())
            mLocationClient.start();
        //开启方向传感器
        mMyOrientationListener.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mNaviHelper.resume();
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
        mNaviHelper.pause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //停止定位
        mBaiduMap.setMyLocationEnabled(false);
        mLocationClient.stop();
        //停止方向传感器
        mMyOrientationListener.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mNaviHelper.quit();
        mSearch.destroy();
        mBaiduMap.setMyLocationEnabled(false);
        mMapView.onDestroy();
        mMapView = null;
    }
}