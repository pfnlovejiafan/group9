package com.example.day6_one.fragment;

import com.example.day6_one.base.BasePresenter;
import com.example.day6_one.callback.RxCallback;
import com.example.day6_one.model.HomeModel;

/**
 * HomeFragment的P层方法
 */
public class HomePresenter extends BasePresenter {

    private HomeModel model;
    //将V层给传送过来
    public HomeFragment homeFragment;



    //接下来向M层进行数据请求
    //提供一个无参构造方法
    public HomePresenter (HomeFragment homeFragment){
        this.homeFragment=homeFragment;
        model=new HomeModel();
    }

    //start是P层中的方法
    @Override
    public void start() {
        //实现M层的自定义方法   然后在回传给V层
        model.requestData(new RxCallback() {
            @Override
            public void onSuccessData(Object o) {
                //返回给V层
                homeFragment.onScuccess(o);
            }

            @Override
            public void onErrorMsg(String msg) {
                homeFragment.onError(msg);
            }
        });
    }
}
