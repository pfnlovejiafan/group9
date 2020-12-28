package com.example.myapplication;


import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
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
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, PoiListAdapter.OnGetChildrenLocationListener{

    private MapView maps;
    private LocationClient locationClient;
    private PoiSearch poiSearch;
    private BaiduMap map;
    private EditText et1;
    private EditText et2;
    private Button bt;

    private RelativeLayout mPoiDetailView;
    private List<PoiInfo> allPoi;
    private BitmapDescriptor mBitmap = BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher_background);
    private ListView lists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );
        initView ();
    }

    private void initView() {
        maps = (MapView) findViewById ( R.id.map );
        et1 = (EditText) findViewById ( R.id.et1 );
        et1.setOnClickListener ( this );
        et2 = (EditText) findViewById ( R.id.et2 );
        et2.setOnClickListener ( this );
        bt = (Button) findViewById ( R.id.bt );
        bt.setOnClickListener ( this );
        lists = findViewById ( R.id.lists );


        mPoiDetailView = (RelativeLayout) findViewById ( R.id.mPoiDetailView );
        mPoiDetailView.setOnClickListener ( this );
        map = this.maps.getMap ();
        map.setMapType ( BaiduMap.MAP_TYPE_NORMAL );
        //定位初始化
        map.setMyLocationEnabled ( true );

        locationClient = new LocationClient ( this );

//通过LocationClientOption设置LocationClient相关参数
        LocationClientOption option = new LocationClientOption ();
        option.setOpenGps ( true ); // 打开gps
        option.setCoorType ( "bd09ll" ); // 设置坐标类型
        option.setScanSpan ( 90000 );

//设置locationClientOption
        locationClient.setLocOption ( option );
        option.setIsNeedAddress ( true );
//可选，设置是否需要地址描述
        option.setIsNeedLocationDescribe ( true );
//可选，设置是否需要设备方向结果
        option.setNeedDeviceDirect ( false );
//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setLocationNotify ( true );
//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.setIgnoreKillProcess ( true );
//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationDescribe ( true );
//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIsNeedLocationPoiList ( true );
//可选，默认false，设置是否收集CRASH信息，默认收集
        option.SetIgnoreCacheException ( false );
//可选，默认false，设置是否开启Gps定位
        option.setOpenGps ( true );
//可选，默认false，设置定位时是否需要海拔信息，默认不需要，除基础定位版本都可用
        option.setIsNeedAltitude ( false );
//设置打开自动回调位置模式，该开关打开后，期间只要定位SDK检测到位置变化就会主动回调给开发者，该模式下开发者无需再关心定位间隔是多少，定位SDK本身发现位置变化就会及时回调给开发者
        option.setOpenAutoNotifyMode ();
//设置打开自动回调位置模式，该开关打开后，期间只要定位SDK检测到位置变化就会主动回调给开发者
        option.setOpenAutoNotifyMode ( 3000, 1, LocationClientOption.LOC_SENSITIVITY_HIGHT );
//需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
        locationClient.setLocOption ( option );


//注册LocationListener监听器
        MyLocationListener myLocationListener = new MyLocationListener ();
        locationClient.registerLocationListener ( myLocationListener );
//开启地图定位图层
        locationClient.start ();
        poiSearch = PoiSearch.newInstance ();
        /**
         *  PoiCiySearchOption 设置检索属性
         *  city 检索城市
         *  keyword 检索内容关键字
         *  pageNum 分页页码
         */


    }




    @Override
    public void onClick(View v) {
        switch (v.getId ()) {
            case R.id.bt:
                String chen = et1.getText ().toString ();
                String xing = et2.getText ().toString ();
                if (!TextUtils.isEmpty ( chen ) && !TextUtils.isEmpty ( xing )) {
                    poiSearch.searchInCity ( new PoiCitySearchOption ()
                            .city ( chen ) //必填
                            .keyword ( xing ) //必填
                            .cityLimit ( false )
                            .pageNum ( 10 ) );
                    Log.e ( "111", "运行到了"+chen+"+"+xing );

                    break;
                }
        }


    }
    private OnGetPoiSearchResultListener listener = new OnGetPoiSearchResultListener () {
        @Override
        public void onGetPoiResult(final PoiResult poiResult) {
            if (poiResult.error == SearchResult.ERRORNO.NO_ERROR) {
                map.clear ();  //创建PoiOverlay对象
                PoiOverlay poiOverlay = new PoiOverlay ( map );
                //设置Poi检索数据
                poiOverlay.setData ( poiResult );
                //将poiOverlay添加至地图并缩放至合适级别
                poiOverlay.addToMap ();
                poiOverlay.zoomToSpan ();
            }
            if (poiResult.error == SearchResult.ERRORNO.NO_ERROR) {
                showPoiDetailView ( true );
                map.clear ();

                // 监听 View 绘制完成后获取view的高度
                mPoiDetailView.getViewTreeObserver ().addOnGlobalLayoutListener ( new ViewTreeObserver.OnGlobalLayoutListener () {
                    @Override
                    public void onGlobalLayout() {
                        int padding = 50;
                        // 添加poi
                        PoiOverlay overlay = new MainActivity .MyPoiOverlay ( map );
                        map.setOnMarkerClickListener ( overlay );
                        overlay.setData ( poiResult );
                        overlay.addToMap ();
                        // 获取 view 的高度
                        int PaddingBootom = mPoiDetailView.getMeasuredHeight ();
                        // 设置显示在规定宽高中的地图地理范围
                        overlay.zoomToSpanPaddingBounds ( padding, padding, padding, PaddingBootom );
                        // 加载完后需要移除View的监听，否则会被多次触发
                        mPoiDetailView.getViewTreeObserver ().removeOnGlobalLayoutListener ( this );
                    }
                } );

                // 获取poi结果
                allPoi = poiResult.getAllPoi ();
                PoiListAdapter poiListAdapter = new PoiListAdapter ( MainActivity.this, allPoi );
                poiListAdapter.setOnGetChildrenLocationListener (MainActivity.this );
                // 把poi结果添加到适配器
                lists.setAdapter ( poiListAdapter );

                return;
            }

            if (poiResult.error == SearchResult.ERRORNO.AMBIGUOUS_KEYWORD) {
                // 当输入关键字在本市没有找到，但在其他城市找到时，返回包含该关键字信息的城市列表
                String strInfo = "在";

                for (CityInfo cityInfo : poiResult.getSuggestCityList ()) {
                    strInfo += cityInfo.city;
                    strInfo += ",";
                }
                strInfo += "找到结果";
                Toast.makeText ( MainActivity.this, strInfo, Toast.LENGTH_LONG ).show ();
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
    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //mapView 销毁后不在处理新接收的位置
            if (location == null || maps == null) {
                return;
            }
            MyLocationData locData = new MyLocationData.Builder ()
                    .accuracy ( location.getRadius () )
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction ( location.getDirection () ).latitude ( location.getLatitude () )
                    .longitude ( location.getLongitude () ).build ();
            map.setMyLocationData ( locData );
        }
    }


    public void getChildrenLocation(LatLng childrenLocation) {
        addPoiLoction(childrenLocation);

    }

    protected class MyPoiOverlay extends PoiOverlay {
        MyPoiOverlay(BaiduMap baiduMap) {
            super ( baiduMap );
        }

        @Override
        public boolean onPoiClick(int index) {
            super.onPoiClick ( index );
            PoiInfo poi = getPoiResult ().getAllPoi ().get ( index );
            Toast.makeText ( MainActivity.this, poi.address, Toast.LENGTH_LONG ).show ();
            return true;
        }
    }

    private void showPoiDetailView(boolean b) {
        if (b) {
            mPoiDetailView.setVisibility ( View.VISIBLE );


        } else {
            mPoiDetailView.setVisibility ( View.GONE );


        }

    }



    @Override
    protected void onResume() {
        super.onResume ();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        maps.onResume ();
    }
    private void addPoiLoction(LatLng latLng) {
        map.clear();
        showPoiDetailView(false);
        OverlayOptions markerOptions = new MarkerOptions ().position(latLng).icon(mBitmap);
        map.addOverlay(markerOptions);
        MapStatus.Builder builder = new MapStatus.Builder();
        builder.target(latLng);
        builder.zoom(18.0f);
        map.setMapStatus( MapStatusUpdateFactory.newMapStatus(builder.build()));
    }

    @Override
    protected void onPause() {
        super.onPause ();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        maps.onPause ();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy ();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        maps.onDestroy ();
        poiSearch.destroy ();

    }


}
