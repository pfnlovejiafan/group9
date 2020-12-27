package com.example.mvp.mvp.ui.fragment;

import android.util.Log;
import android.widget.TextView;

import com.example.mvp.R;
import com.example.mvp.base.BaseFragment;
import com.example.mvp.base.BasePresenter;
import com.example.mvp.base.IBaseView;
import com.example.mvp.mvp.presenter.HomeFrgPresenter;

import butterknife.BindView;

public class HomeFragment extends BaseFragment{
    @BindView(R.id.homt_frg_tv)
    TextView mHomtFrgTv;

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
        Log.e("TAG", str+"================");
    }


    @Override
    public void onError(String msg) {
        Log.e("TAG", msg+"================");
    }
}