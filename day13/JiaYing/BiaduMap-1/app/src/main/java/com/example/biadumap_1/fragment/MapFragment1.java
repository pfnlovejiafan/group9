package com.example.biadumap_1.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapBaseIndoorMapInfo;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.CityInfo;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.sug.OnGetSuggestionResultListener;
import com.baidu.mapapi.search.sug.SuggestionResult;
import com.baidu.mapapi.search.sug.SuggestionSearch;
import com.example.biadumap_1.Item.Cluster;
import com.example.biadumap_1.Item.ClusterItem;
import com.example.biadumap_1.MapFragment2.util.PoiOverlay;

import com.example.biadumap_1.R;
import com.example.biadumap_1.activity.MainActivity;
import com.example.biadumap_1.mClusterManager.ClusterManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Ix1biG1NK2IiTQHi131n911RAkTSdpV0
 * 显示地图
 * POI检索+POI提示检索功能
 */
public class MapFragment1 extends Fragment implements BaiduMap.OnMapLoadedCallback {

    @BindView(R.id.pt)
    RadioButton pt;
    @BindView(R.id.wx)
    RadioButton wx;
    @BindView(R.id.nullwhite)
    RadioButton nullwhite;
    @BindView(R.id.timemap)
    RadioButton timemap;
    @BindView(R.id.hot)
    RadioButton hot;
    @BindView(R.id.btn_clear)
    Button btnClear;
    @BindView(R.id.rg)
    RadioGroup rg;
    @BindView(R.id.et_city)
    EditText etCity;
    @BindView(R.id.et_poi)
    EditText etPoi;
    @BindView(R.id.btn_search)
    Button btnSearch;
    @BindView(R.id.ll1)
    LinearLayout ll1;
    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.bmapView_fragment1)
    MapView mMapView;
    private String strFloor;
    private String strID;
    private MapBaseIndoorMapInfo.SwitchFloorError switchFloorError;
    private ClusterManager<MyItem> mClusterManager;
    private PoiSearch mPoiSearch;
    private LocationClient mLocationClient;
    private MapStatus mMapStatus;
    private BaiduMap mBaiduMap;
    private BitmapDescriptor mBitmap = BitmapDescriptorFactory.fromResource(R.drawable.icon_marka);
    private List<PoiInfo> mAllPoi;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map1, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView(getView());
    }

    private void initView(View view) {
        //获取地图控件引用
        pt = view.findViewById(R.id.pt);
        wx = view.findViewById(R.id.wx);
        nullwhite = view.findViewById(R.id.nullwhite);
        timemap = view.findViewById(R.id.timemap);
        hot = view.findViewById(R.id.hot);
        btnClear = view.findViewById(R.id.btn_clear);
        btnSearch = view.findViewById(R.id.btn_search);
        lv = view.findViewById(R.id.lv);
        mMapView = view.findViewById(R.id.bmapView_fragment1);
        rg = view.findViewById(R.id.rg);


        //rg的监听  切换地图类型
        //获取map
        mBaiduMap = mMapView.getMap();
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.pt:
                        //普通地图 ,mBaiduMap是地图控制器对象
                        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                        break;

                    case R.id.wx:
                        //卫星地图
                        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
                        break;
                    case R.id.timemap:
                        //开启交通图
                        mBaiduMap.setTrafficEnabled(true);
                        break;
                    case R.id.nullwhite:
                        //空白地图
                        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NONE);
                        break;
                    case R.id.hot:
                        //开启热力图
                        mBaiduMap.setBaiduHeatMapEnabled(true);
                        break;
                }
            }
        });

     //清除缓存
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BaiduMap mMapViewMap = mMapView.getMap();
                mMapViewMap.clear();
            }
        });

        //开启地图的定位图层
        mBaiduMap.setMyLocationEnabled(true);
        //通过LocationClient发起定位
        //定位初始化
        mLocationClient = new LocationClient(getContext());


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


        //室内地图的设置
        mBaiduMap.setIndoorEnable(true);//打开室内图，默认为关闭状态
        mBaiduMap.setOnBaseIndoorMapListener(new BaiduMap.OnBaseIndoorMapListener() {
            @Override
            public void onBaseIndoorMapMode(boolean on, MapBaseIndoorMapInfo mapBaseIndoorMapInfo) {
                if (on) {
                    // 进入室内图
                    // 通过获取回调参数 mapBaseIndoorMapInfo 便可获取室内图信
                    //息，包含楼层信息，室内ID等
                    // 切换楼层信息
                    //strID 通过 mMapBaseIndoorMapInfo.getID()方法获得
                    switchFloorError = mBaiduMap.switchBaseIndoorMapFloor(strFloor, strID);

                } else {
                    // 移除室内图
                }
            }
        });


        //点聚合的设置
        mMapStatus = new MapStatus.Builder().target(new LatLng(39.914935, 116.403119)).zoom(8).build();
        mBaiduMap.setOnMapLoadedCallback(this);
        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(mMapStatus));
        //初始化点聚合管理类
        mClusterManager = new ClusterManager<MyItem>(getContext(), mBaiduMap);
        // 添加Marker点
        addMarkers();
        // 设置地图监听，当地图状态发生改变时，进行点聚合运算
        mBaiduMap.setOnMapStatusChangeListener(mClusterManager);
        // 设置maker点击时的响应
        mBaiduMap.setOnMarkerClickListener(mClusterManager);

        mClusterManager.setOnClusterClickListener(new ClusterManager.OnClusterClickListener<MyItem>() {
            @Override
            public boolean onClusterClick(Cluster<MyItem> cluster) {
                Toast.makeText(getActivity(), "有" + cluster.getSize() + "个点", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        mClusterManager.setOnClusterItemClickListener(new ClusterManager.OnClusterItemClickListener<MyItem>() {
            @Override
            public boolean onClusterItemClick(MyItem item) {
                Toast.makeText(getActivity(), "点击单个Item", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        //城市内检索
        mPoiSearch = PoiSearch.newInstance();
        mPoiSearch.setOnGetPoiSearchResultListener(listener);
    }


    private OnGetPoiSearchResultListener listener = new OnGetPoiSearchResultListener() {
        @Override
        public void onGetPoiResult(PoiResult poiResult) {
            if (poiResult.error == SearchResult.ERRORNO.NO_ERROR) {
                mBaiduMap.clear();
                //创建PoiOverlay对象
                PoiOverlay poiOverlay = new PoiOverlay(mBaiduMap);

                //设置Poi检索数据
                poiOverlay.setData(poiResult);

                //将poiOverlay添加至地图并缩放至合适级别
                poiOverlay.addToMap();
                poiOverlay.zoomToSpan();
            }

        }

        @Override
        public void onGetPoiDetailResult(PoiDetailSearchResult poiDetailSearchResult) {

        }

        @Override
        public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

        }

        //废弃
        @Override
        public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

        }
    };

    @OnClick({R.id.btn_search})
    public void click(View view) {
        double latitude = Double.parseDouble("40.142471");
        double longitude = Double.parseDouble("116.215194");
        int radius = Integer.parseInt("1000");
        LatLng latLng = new LatLng(latitude, longitude);
        Log.e("TAG", "1111111");//116.215194,
        // 配置请求参数
        PoiNearbySearchOption nearbySearchOption = new PoiNearbySearchOption()
                .keyword("餐厅") // 检索关键字
                .location(latLng) // 经纬度
                .radius(radius) // 检索半径 单位： m
                .pageNum(0) // 分页编号
                .radiusLimit(false)
                .scope(2);
        // 发起检索
        mPoiSearch.searchNearby(nearbySearchOption);
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


    //构造地图数据
    //我们通过继承抽象类BDAbstractListener并重写其onReceieveLocation方法来获取定位数据，并将其传给MapView。
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
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
        mLocationClient.stop();
        mBaiduMap.setMyLocationEnabled(false);
        mMapView = null;
    }
}