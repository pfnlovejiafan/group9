package com.example.baidumap.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.sug.OnGetSuggestionResultListener;
import com.baidu.mapapi.search.sug.SuggestionResult;
import com.baidu.mapapi.search.sug.SuggestionSearch;
import com.baidu.mapapi.search.sug.SuggestionSearchOption;
import com.example.baidumap.App;
import com.example.baidumap.R;
import com.example.baidumap.adapter.HomeActRlAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PoiSugSearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PoiSugSearchFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    @BindView(R.id.et_city)
    EditText etCity;
    @BindView(R.id.et_poi)
    EditText etPoi;
    @BindView(R.id.bmapView)
    MapView mMapView;
    @BindView(R.id.rl_suggest)
    RecyclerView mRl;
    private InfoWindow mInfoWindow;

    private BitmapDescriptor mBitmap = BitmapDescriptorFactory.fromResource(R.drawable.icon_marka);

    private Unbinder mBind;
    private SuggestionSearch mSuggestionSearch;
    private List<SuggestionResult.SuggestionInfo> allSuggestions;
    private HomeActRlAdapter actRlAdapter;
    private BaiduMap mBaiduMap;

    public PoiSugSearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PoiSugSearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PoiSugSearchFragment newInstance(String param1, String param2) {
        PoiSugSearchFragment fragment = new PoiSugSearchFragment();
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
        View inflate = inflater.inflate(R.layout.fragment_poi_sug_search, container, false);
        initView(inflate);
        return inflate;
    }


    private void initView(View inflate) {
        mBind = ButterKnife.bind(this,inflate);

        mBaiduMap = mMapView.getMap();

        mSuggestionSearch = SuggestionSearch.newInstance();
        mSuggestionSearch.setOnGetSuggestionResultListener(listener);
        String cityName = etCity.getText().toString();


        etPoi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mRl.setVisibility(View.VISIBLE);
                mSuggestionSearch.requestSuggestion(new SuggestionSearchOption()
                        .city(cityName)
                        .keyword(s + ""));
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });



    }
    /**
     * 更新到子节点的位置
     *
     * @param latLng 子节点经纬度
     */
    private void addPoiLoction (LatLng latLng){
        mBaiduMap.clear();
        OverlayOptions markerOptions = new MarkerOptions().position(latLng).icon(mBitmap).clickable(true);
        mBaiduMap.addOverlay(markerOptions);
        MapStatus.Builder builder = new MapStatus.Builder();
        builder.target(latLng);
        builder.zoom(18.0f);
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));

    }


    private void infoWindows (String desc, LatLng point){
        //用来构造InfoWindow的Button
        Button button = new Button(App.getContent());
        button.setBackgroundResource(R.drawable.popup);
        button.setText(desc);

        //构造InfoWindow
        //point 描述的位置点
        //-100 InfoWindow相对于point在y轴的偏移量
        InfoWindow infoWindow = new InfoWindow(button, point, -20);
        //使InfoWindow生效
        mBaiduMap.showInfoWindow(infoWindow);
    }


    private OnGetSuggestionResultListener listener = new OnGetSuggestionResultListener() {
        @Override
        public void onGetSuggestionResult(SuggestionResult suggestionResult) {
            //处理sug检索结果
            allSuggestions = suggestionResult.getAllSuggestions();
            if (allSuggestions != null && allSuggestions.size() > 0) {
                mRl.setLayoutManager(new LinearLayoutManager(App.getContent()));
                mRl.addItemDecoration(new DividerItemDecoration(App.getContent(), DividerItemDecoration.VERTICAL));
                actRlAdapter = new HomeActRlAdapter(App.getContent(), allSuggestions);
                mRl.setAdapter(actRlAdapter);

                actRlAdapter.setiOnClick(new HomeActRlAdapter.IOnClick() {
                    @Override
                    public void click(int pos) {
                        mRl.setVisibility(View.GONE);
                        if (allSuggestions != null && allSuggestions.size() > 0) {
                            SuggestionResult.SuggestionInfo suggestionInfo = allSuggestions.get(pos);
                            LatLng pt = suggestionInfo.pt;

                            addPoiLoction(pt);

//                            initListener(suggestionInfo.address);

//                        PoiInfo poiInfo = suggestionInfo;
//                        MapStatusUpdate status = MapStatusUpdateFactory.newLatLng(poiInfo.location);
//                        mBaiduMap.setMapStatus(status);

                            infoWindows(suggestionInfo.key, pt);
                            etPoi.setText(suggestionInfo.key);
                            mRl.setVisibility(View.GONE);
                        }
                    }
                });
            }
        }
    };

    private void initListener (String address){
        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            public boolean onMarkerClick(final Marker marker) {
                Button button = new Button(App.getContent());
                button.setBackgroundResource(R.drawable.popup);
                InfoWindow.OnInfoWindowClickListener listener = null;

                button.setText(address);
                button.setTextColor(Color.BLACK);
                button.setWidth(300);
                // InfoWindow点击事件监听接口
                listener = new InfoWindow.OnInfoWindowClickListener() {
                    public void onInfoWindowClick() {
                        LatLng latLng = marker.getPosition();
                        LatLng latLngNew = new LatLng(latLng.latitude + 0.005, latLng.longitude + 0.005);
                        marker.setPosition(latLngNew);
                        // 隐藏地图上的所有InfoWindow
                        mBaiduMap.hideInfoWindow();
                    }
                };
                LatLng latLng = marker.getPosition();
                // 创建InfoWindow
                mInfoWindow = new InfoWindow(BitmapDescriptorFactory.fromView(button), latLng, -47, listener);
                // 显示 InfoWindow, 该接口会先隐藏其他已添加的InfoWindow, 再添加新的InfoWindow
                mBaiduMap.showInfoWindow(mInfoWindow);

                return true;
            }
        });
    }

    @Override
    public void onResume () {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    public void onPause () {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
        mBind.unbind();
        mSuggestionSearch.destroy();
        mBaiduMap.clear();
        mBitmap.recycle();
    }
}