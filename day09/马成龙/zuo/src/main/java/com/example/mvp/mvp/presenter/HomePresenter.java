package com.example.mvp.mvp.presenter;

import com.example.mvp.base.BasePresenter;
import com.example.mvp.callback.RxCallBack;
import com.example.mvp.mvp.model.HomeModel;
import com.example.mvp.mvp.ui.fragment.HomeFragment;

public class HomePresenter extends BasePresenter {
    private HomeModel model;
    private HomeFragment mFragment;

    public HomePresenter(HomeFragment fragment) {
        this.mFragment = fragment;
        model = new HomeModel();
    }

    @Override
    public void start() {
        //2.P层收到指令之后要向M层进行进行数据请求
        model.requestData(new RxCallBack() {
            @Override
            public void onSuccessData(Object o) {

                //4.P层再将数据交给V层更新UI
                mFragment.onScuccess(o);
            }

            @Override
            public void onErrorMsg(String msg) {
                //4.P层再将数据交给V层更新UI
                mFragment.onError(msg);
            }
        });
    }
}