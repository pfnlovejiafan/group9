package com.example.ceshi1.mvp.mvp.ui.fragment;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.example.ceshi1.R;
import com.example.ceshi1.mvp.base.BaseFragment;
import com.example.ceshi1.mvp.base.BasePresenter;
import com.example.ceshi1.mvp.mvp.presenter.HomeFrgPresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeFragment extends BaseFragment {

    @Override
    protected BasePresenter createPresenter() {
        return new HomeFrgPresenter(this);
    }

    @Override
    protected void initData() {
        getmPresenter().start();
    }

    @Override
    protected void init() {

    }


    @Override
    protected int getLayoutId() {
        return R.layout.home_fragment;
    }

    @Override
    public void onScuccess(Object obj) {
        String str = (String) obj;
        Log.e("TAG", str + "================");
    }


    @Override
    public void onError(String msg) {
        Log.e("TAG", msg + "================");
    }
}