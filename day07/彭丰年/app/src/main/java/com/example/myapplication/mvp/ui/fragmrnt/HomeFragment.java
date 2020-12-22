package com.example.myapplication.mvp.ui.fragmrnt;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.bean.BaseFragement;
import com.example.myapplication.bean.BasePresenter;
import com.example.myapplication.bean.IBaseView;
import com.example.myapplication.mvp.presenter.HomePresenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomeFragment extends BaseFragement {
    @Override
    protected BasePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initData() {
        getmPresenter().start();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_it;
    }

    @Override
    public void ShowOk(Object object) {
        String str= (String) object;
        Log.e("TAG",str);
    }

    @Override
    public void ShowNo(String string) {
        Log.e("TAG","请求失败"+string);
    }
}
