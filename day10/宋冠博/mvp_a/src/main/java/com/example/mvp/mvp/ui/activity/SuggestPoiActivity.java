package com.example.mvp.mvp.ui.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.search.sug.OnGetSuggestionResultListener;
import com.baidu.mapapi.search.sug.SuggestionResult;
import com.baidu.mapapi.search.sug.SuggestionSearch;
import com.baidu.mapapi.search.sug.SuggestionSearchOption;
import com.example.mvp.R;
import com.example.mvp.engine.adapter.HomeActRlAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SuggestPoiActivity extends AppCompatActivity {
    @BindView(R.id.et_city)
    EditText etCity;
    @BindView(R.id.et_poi)
    EditText etPoi;
    @BindView(R.id.bmapView)
    MapView mMapView;
    @BindView(R.id.rl_suggest)
    RecyclerView mRl;

    private Unbinder mBind;
    private SuggestionSearch mSuggestionSearch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_suggest_poi);
        mBind = ButterKnife.bind(this);
        mSuggestionSearch = SuggestionSearch.newInstance();
        mSuggestionSearch.setOnGetSuggestionResultListener(listener);
        String cityName = etCity.getText().toString();


        etPoi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mSuggestionSearch.requestSuggestion(new SuggestionSearchOption()
                        .city(cityName)
                        .keyword(s + ""));
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }


    private OnGetSuggestionResultListener listener = new OnGetSuggestionResultListener() {
        @Override
        public void onGetSuggestionResult(SuggestionResult suggestionResult) {
            //处理sug检索结果
            List<SuggestionResult.SuggestionInfo> allSuggestions = suggestionResult.getAllSuggestions();
             if(allSuggestions!=null&&allSuggestions.size()>0) {
                 mRl.setLayoutManager(new LinearLayoutManager(SuggestPoiActivity.this));
                 mRl.addItemDecoration(new DividerItemDecoration(SuggestPoiActivity.this, DividerItemDecoration.VERTICAL));
                 HomeActRlAdapter actRlAdapter = new HomeActRlAdapter(SuggestPoiActivity.this, allSuggestions);
                 mRl.setAdapter(actRlAdapter);
             }
        }
    };

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
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
        mBind.unbind();
        mSuggestionSearch.destroy();
    }
}