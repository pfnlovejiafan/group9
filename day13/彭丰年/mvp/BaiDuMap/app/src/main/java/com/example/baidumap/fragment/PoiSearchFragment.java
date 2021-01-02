package com.example.baidumap.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
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
import com.example.baidumap.App;
import com.example.baidumap.R;
import com.example.baidumap.adapter.PoiListAdapter;
import com.example.baidumap.map.overlayutil.KeybordUtil;
import com.example.baidumap.map.overlayutil.PoiOverlay;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PoiSearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PoiSearchFragment extends Fragment implements OnGetPoiSearchResultListener, PoiListAdapter.OnGetChildrenLocationListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private PoiSearch mPoiSearch = null;
    private MapView mMapView = null;
    private BaiduMap mBaiduMap = null;
    // 搜索关键字输入窗口
    private EditText mEditCity = null;
    private AutoCompleteTextView mKeyWordsView = null;
    private RelativeLayout mPoiDetailView;
    private ListView mPoiList;

    private List<PoiInfo> mAllPoi;
    private BitmapDescriptor mBitmap = BitmapDescriptorFactory.fromResource(R.drawable.icon_marka);


    public PoiSearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PoiSearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PoiSearchFragment newInstance(String param1, String param2) {
        PoiSearchFragment fragment = new PoiSearchFragment();
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
        View inflate = inflater.inflate(R.layout.fragment_poi_search, container, false);
        initView(inflate);
        return inflate;
    }

    private void initView(View inflate) {
        // 创建map
        mMapView = (MapView) inflate.findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();

        // 创建poi检索实例，注册搜索事件监听
        mPoiSearch = PoiSearch.newInstance();
        mPoiSearch.setOnGetPoiSearchResultListener(this);
        mEditCity = (EditText) inflate.findViewById(R.id.city);
        mKeyWordsView = (AutoCompleteTextView) inflate.findViewById(R.id.searchkey);

        //设置检索监听器结果
        mPoiSearch.setOnGetPoiSearchResultListener(listener);
        //地图点击事件
        mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                showPoiDetailView(false);
            }

            @Override
            public void onMapPoiClick(MapPoi mapPoi) {

            }
        });

        Button search = inflate.findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  按搜索按钮时隐藏软件盘，为了在结果回调时计算 PoiDetailView 控件的高度，把地图中poi展示到合理范围内
                KeybordUtil.closeKeybord(getActivity());
                // 获取检索城市
                String cityStr = mEditCity.getText().toString();
                // 获取检索关键字
                String keyWordStr = mKeyWordsView.getText().toString();


                // 发起请求
                mPoiSearch.searchInCity((new PoiCitySearchOption())
                        .city(cityStr)
                        .keyword(keyWordStr)
                        .pageNum(0) // 分页编号
                        .cityLimit(false)
                        .scope(2));
            }
        });

        // 展示父子节点控件
        mPoiDetailView = (RelativeLayout) inflate.findViewById(R.id.poi_detail);
        mPoiList = (ListView) inflate.findViewById(R.id.poi_list);
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

    // 创建POI检索监听器
    OnGetPoiSearchResultListener listener = new OnGetPoiSearchResultListener() {
        @Override
        public void onGetPoiResult(PoiResult result) {
            if (result == null || result.error == SearchResult.ERRORNO.RESULT_NOT_FOUND) {
                mBaiduMap.clear();
                showPoiDetailView(false);
                Toast.makeText(App.getContent(), "未找到结果", Toast.LENGTH_LONG).show();
                return;
            }

            if (result.error == SearchResult.ERRORNO.NO_ERROR) {
                mBaiduMap.clear();
                showPoiDetailView(true);
                // 监听 View 绘制完成后获取view的高度
                mPoiDetailView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        int padding = 50;
                        // 添加poi
                        PoiOverlay overlay = new MyPoiOverlay(mBaiduMap);
                        mBaiduMap.setOnMarkerClickListener(overlay);
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
                PoiListAdapter poiListAdapter = new PoiListAdapter(App.getContent(), mAllPoi);
                poiListAdapter.setOnGetChildrenLocationListener(PoiSearchFragment.this);
                // 把poi结果添加到适配器
                mPoiList.setAdapter(poiListAdapter);

//                //点击条目进行定位
//                PoiInfo poiInfo = mAllPoi.get(pos);
//                MapStatusUpdate status = MapStatusUpdateFactory.newLatLng(poiInfo.location);
//                mBaiduMap.setMapStatus(status);


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
                Toast.makeText(App.getContent(), strInfo, Toast.LENGTH_LONG).show();
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
    public void onGetPoiResult(PoiResult poiResult) {

    }

    @Override
    public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

    }

    @Override
    public void onGetPoiDetailResult(PoiDetailSearchResult poiDetailSearchResult) {

    }

    @Override
    public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

    }

    @Override
    public void getChildrenLocation(LatLng childrenLocation) {

    }

    protected class MyPoiOverlay extends PoiOverlay {
        MyPoiOverlay(BaiduMap baiduMap) {
            super(baiduMap);
        }

        @Override
        public boolean onPoiClick(int index) {
            super.onPoiClick(index);
            PoiInfo poi = getPoiResult().getAllPoi().get(index);
            Toast.makeText(App.getContent(), poi.address, Toast.LENGTH_LONG).show();
            return true;
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        if (mMapView != null) {
            mMapView.onResume();
        }
        // 隐藏控件
        showPoiDetailView(false);
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

        if (mBitmap != null) {
            // 释放bitmap
            mBitmap.recycle();
        }
        if (mPoiSearch != null) {
            // 释放检索对象
            mPoiSearch.destroy();
        }
        if (mBitmap != null) {
            // 清空地图所有的覆盖物
            mBaiduMap.clear();
        }

        if (mMapView != null) {
            mMapView.onDestroy();
            mMapView = null;
            //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        }

    }

}