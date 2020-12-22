package com.example.myapplication.mvp.presenter;

import com.example.myapplication.bean.BasePresenter;
import com.example.myapplication.callback.RxCallBack;
import com.example.myapplication.mvp.ui.fragmrnt.HomeFragment;

public class HomePresenter extends BasePresenter {
    private HomeModel model;
    private HomeFragment mFragment;

    public HomePresenter(HomeFragment fragment) {
        this.mFragment = fragment;
        model = new HomeModel();
    }

    @Override
    public void start() {
        model.json(new RxCallBack() {
            @Override
            public void JsonOk(Object object) {
                mFragment.ShowOk(object);
            }

            @Override
            public void JsonNo(String string) {
                mFragment.ShowNo(string);
            }
        });
    }
}
