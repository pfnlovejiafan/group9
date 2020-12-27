package com.example.mvp.mvp.ui.activity;

import android.Manifest;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.baidu.location.Poi;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
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
import com.example.mvp.R;
import com.example.mvp.engine.adapter.PoiListAdapter;
import com.example.mvp.util.PoiOverlay;
import com.github.dfqin.grantor.PermissionListener;
import com.github.dfqin.grantor.PermissionsUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * POI城市搜索
 */
public class PoiMainActivity extends AppCompatActivity implements PoiListAdapter.OnGetChildrenLocationListener {
    @BindView(R.id.et_city)
    EditText etCity;
    @BindView(R.id.et_poi)
    EditText etPoi;
    @BindView(R.id.bmapView)
    MapView mMapView;

    @BindView(R.id.poi_detail)
    RelativeLayout mPoiDetailView;
    private List<PoiInfo> mAllPoi;

    @BindView(R.id.poi_list)
    ListView mPoiList;
    private BitmapDescriptor mBitmap = BitmapDescriptorFactory.fromResource(R.drawable.icon_marka);

    private BaiduMap mMap;
    private Unbinder mBind;
    private PoiSearch mPoiSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_poi);
        mBind = ButterKnife.bind(this);
        initPermission();
        //获取地图控件引用
        mMap = mMapView.getMap();

        //创建POI检索实例
        mPoiSearch = PoiSearch.newInstance();
        //设置检索监听器结果
        mPoiSearch.setOnGetPoiSearchResultListener(listener);
    }

    @OnClick(R.id.btn_search)
    public void click(View view) {
        String str_city = etCity.getText().toString();
        String str_poi = etPoi.getText().toString();
        if (!TextUtils.isEmpty(str_city) && !TextUtils.isEmpty(str_poi)) {
            mPoiSearch.searchInCity(new PoiCitySearchOption()
                    .city(str_city) //必填
                    .keyword(str_poi) //必填
                    .cityLimit(false)
                    .scope(2));
        } else {
            Toast.makeText(PoiMainActivity.this, "两个框不能为空", Toast.LENGTH_LONG).show();
        }
    }


    private OnGetPoiSearchResultListener listener = new OnGetPoiSearchResultListener() {
        @Override
        public void onGetPoiResult(PoiResult result) {
            if (result == null || result.error == SearchResult.ERRORNO.RESULT_NOT_FOUND) {
                mMap.clear();
                showPoiDetailView(false);
                Toast.makeText(PoiMainActivity.this, "未找到结果", Toast.LENGTH_LONG).show();
                return;
            }

            if (result.error == SearchResult.ERRORNO.NO_ERROR) {
                showPoiDetailView(true);
                mMap.clear();

                // 监听 View 绘制完成后获取view的高度
                mPoiDetailView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        int padding = 50;
                        // 添加poi
                        PoiOverlay overlay = new PoiMainActivity.MyPoiOverlay(mMap);
                        mMap.setOnMarkerClickListener(overlay);
                        overlay.setData(result);
                        overlay.addToMap();
                        // 获取 view 的高度
                        int PaddingBootom = mPoiDetailView.getMeasuredHeight();
                        // 设置显示在规定宽高中的地图地理范围
                        overlay.zoomToSpanPaddingBounds(padding, padding, padding, PaddingBootom);
                        // 加载完后需要移除View的监听，否则会被多次触发
                        mPoiDetailView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });

                // 获取poi结果
                mAllPoi = result.getAllPoi();
                PoiListAdapter poiListAdapter = new PoiListAdapter(PoiMainActivity.this, mAllPoi);
                poiListAdapter.setOnGetChildrenLocationListener(PoiMainActivity.this);
                // 把poi结果添加到适配器
                mPoiList.setAdapter(poiListAdapter);

                return;
            }

            if (result.error == SearchResult.ERRORNO.AMBIGUOUS_KEYWORD) {
                // 当输入关键字在本市没有找到，但在其他城市找到时，返回包含该关键字信息的城市列表
                String strInfo = "在";

                for (CityInfo cityInfo : result.getSuggestCityList()) {
                    strInfo += cityInfo.city;
                    strInfo += ",";
                }
                strInfo += "找到结果";
                Toast.makeText(PoiMainActivity.this, strInfo, Toast.LENGTH_LONG).show();
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

    @Override
    public void getChildrenLocation(LatLng childrenLocation) {
        addPoiLoction(childrenLocation);
    }


    /**
     * 更新到子节点的位置
     *
     * @param latLng 子节点经纬度
     */
    private void addPoiLoction(LatLng latLng) {
        mMap.clear();
        showPoiDetailView(false);
        OverlayOptions markerOptions = new MarkerOptions().position(latLng).icon(mBitmap);
        mMap.addOverlay(markerOptions);
        MapStatus.Builder builder = new MapStatus.Builder();
        builder.target(latLng);
        builder.zoom(18.0f);
        mMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
    }

    protected class MyPoiOverlay extends PoiOverlay {
        MyPoiOverlay(BaiduMap baiduMap) {
            super(baiduMap);
        }

        @Override
        public boolean onPoiClick(int index) {
            super.onPoiClick(index);
            PoiInfo poi = getPoiResult().getAllPoi().get(index);
            Toast.makeText(PoiMainActivity.this, poi.address, Toast.LENGTH_LONG).show();
            return true;
        }
    }

    /**
     * 是否展示详情 view
     */
    private void showPoiDetailView(boolean whetherShow) {
        if (whetherShow) {
            mPoiDetailView.setVisibility(View.VISIBLE);
        } else {
            mPoiDetailView.setVisibility(View.GONE);
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
        // 释放bitmap
        mBitmap.recycle();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
        mBind.unbind();
        mPoiSearch.destroy();


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


}