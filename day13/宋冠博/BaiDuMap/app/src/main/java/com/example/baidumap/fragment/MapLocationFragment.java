package com.example.baidumap.fragment;

import android.Manifest;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.example.baidumap.App;
import com.example.baidumap.R;
import com.github.dfqin.grantor.PermissionListener;
import com.github.dfqin.grantor.PermissionsUtil;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MapLocationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapLocationFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private MapView mMapView = null;
    //百度地图的数据操作
    private BaiduMap mBaiduMap;
    //百度地图定位的类
    private LocationClient mLocationClient;

    private static final String TAG = MapLocationFragment.class.getSimpleName();

    public MapLocationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MapLocationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MapLocationFragment newInstance(String param1, String param2) {
        MapLocationFragment fragment = new MapLocationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_map_location, container, false);
        initView(inflate);
        return inflate;
    }

    private void initView(View inflate) {
        //获取地图控件引用
        mMapView = (MapView) inflate.findViewById(R.id.bmapView);

        mBaiduMap = mMapView.getMap();

        mBaiduMap.setMyLocationEnabled(true);
        //设置为普通类型的地图
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);

        initLocation();
    }


    /**
     * 初始化定位
     */
    private void initLocation() {

        //定位初始化
        mLocationClient = new LocationClient(App.getContent());
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

    /**
     * 地图定位的监听
     */
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

            Log.e(TAG, "onReceiveLocation: " + "定位的经度：" + location.getLatitude() + "==========" + "定位的维度：" + location.getLongitude());
            mBaiduMap.setMyLocationData(locData);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        if (mMapView != null) {
            mMapView.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        if (mMapView != null) {
            mMapView.onPause();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mLocationClient != null) {
            mLocationClient.stop();
        }
        if (mBaiduMap != null) {
            mBaiduMap.setMyLocationEnabled(false);
        }
        if (mMapView != null) {
            mMapView.onDestroy();
            mMapView = null;
            //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        }


    }
}