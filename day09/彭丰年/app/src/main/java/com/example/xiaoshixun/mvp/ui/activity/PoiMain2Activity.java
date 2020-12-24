package com.example.xiaoshixun.mvp.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.example.xiaoshixun.R;
import com.example.xiaoshixun.util.PoiOverlay;


/**
 * POI周边搜索
 */
public class PoiMain2Activity extends AppCompatActivity {
//    @BindView(R.id.btn_search)
//    Button btnSearch;
//    @BindView(R.id.bmapView)
    MapView bmapView;
    private PoiSearch mPoiSearch;
    private BaiduMap mBaiduMap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_poi2);
//        ButterKnife.bind(this);
        mBaiduMap = bmapView.getMap();

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


//    @OnClick({R.id.btn_search})
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


}